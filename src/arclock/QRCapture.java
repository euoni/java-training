package arclock;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.CRC32;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.opencv_calib3d;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.detector.AlignmentPattern;
import com.google.zxing.qrcode.detector.FinderPattern;

public class QRCapture {
	private final OpenCVFrameGrabber grabber;

	public QRCapture(int deviceNumber, int width, int height) {
		grabber = new OpenCVFrameGrabber(deviceNumber);
		grabber.setImageWidth(width);
		grabber.setImageHeight(height);
	}

	public void start() throws org.bytedeco.javacv.FrameGrabber.Exception {
		grabber.start();
	}

	public void stop() throws org.bytedeco.javacv.FrameGrabber.Exception {
		grabber.stop();
	}

	public BufferedImage process() {
		// grab
		final Frame frame;
		try {
			frame = grabber.grab();
		} catch (final org.bytedeco.javacv.FrameGrabber.Exception e) {
			return null;
		}

		// detect
		final ToMat matConverter = new OpenCVFrameConverter.ToMat();
		final Java2DFrameConverter javaConverter = new Java2DFrameConverter();
		final Mat cvImg = matConverter.convert(frame);
		final BufferedImage bufImg = javaConverter.convert(frame);
		final LuminanceSource source = new BufferedImageLuminanceSource(bufImg);
		final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		final MultipleBarcodeReader reader = new QRCodeMultiReader();
		Result[] decodeResult;
		try {
			decodeResult = reader.decodeMultiple(bitmap);
		} catch (final NotFoundException e) {
			return bufImg;
		}

		// time text
		final LocalDateTime time = LocalDateTime.now();
		final String text = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

		// draw
		for (final Result result : decodeResult) {
			if (result.getResultPoints().length != 4)
				continue;

			final Scalar bgColor = getColor(result.getRawBytes());
			final int size = 500;
			final Mat clock = new Mat(size, size, opencv_core.CV_8UC3, bgColor);
			final Scalar fgColor = getComplementaryColor(bgColor);
			opencv_imgproc.putText(clock, text, new Point(0, 265), opencv_core.FONT_HERSHEY_PLAIN, 7, fgColor, 5,
					opencv_core.LINE_8, false);

			final FinderPattern bottomLeft = (FinderPattern) result.getResultPoints()[0];
			final FinderPattern topLeft = (FinderPattern) result.getResultPoints()[1];
			final FinderPattern topRight = (FinderPattern) result.getResultPoints()[2];
			final AlignmentPattern alignmentPattern = (AlignmentPattern) result.getResultPoints()[3];
			final Mat H;
			try {
				H = createTransform(topLeft, topRight, bottomLeft, alignmentPattern, clock.size(0));
			} catch (final NotFoundException e) {
				continue;
			}

			final Mat wrapedClock = new Mat();
			opencv_imgproc.warpPerspective(clock, wrapedClock, H, cvImg.size(), opencv_imgproc.INTER_LINEAR,
					opencv_core.BORDER_REPLICATE, null);
			final Mat mask = new Mat(clock.size(), opencv_core.CV_8UC1, Scalar.all(255));
			opencv_imgproc.warpPerspective(mask, mask, H, cvImg.size());
			wrapedClock.copyTo(cvImg, mask);
		}

		return javaConverter.convert(matConverter.convert(cvImg));
	}

	private static Scalar getColor(byte[] data) {
		final CRC32 hash = new CRC32();
		hash.update(data);
		final long value = hash.getValue();
		final byte r = (byte) value;
		final byte g = (byte) (value >> 8);
		final byte b = (byte) (value >> 8);
		return new Scalar(b, g, r, 0);
	}

	private static Scalar getComplementaryColor(Scalar color) {
		return new Scalar(255 - color.get(0), 255 - color.get(1), 255 - color.get(2), 0);
	}

	private static Mat createTransform(FinderPattern topLeft, FinderPattern topRight, FinderPattern bottomLeft,
			AlignmentPattern alignmentPattern, float srcSize) throws NotFoundException {
		final float moduleSize = topLeft.getEstimatedModuleSize();
		final int tltrCentersDimension = MathUtils.round(ResultPoint.distance(topLeft, topRight) / moduleSize);
		final int tlblCentersDimension = MathUtils.round(ResultPoint.distance(topLeft, bottomLeft) / moduleSize);
		int dimension = ((tltrCentersDimension + tlblCentersDimension) / 2) + 7;
		switch (dimension & 0x03) { // mod 4
		case 0:
			dimension++;
			break;
		// 1? do nothing
		case 2:
			dimension--;
			break;
		case 3:
			throw NotFoundException.getNotFoundInstance();
		}

		final float dimMinusThree = dimension - 3.5f;
		final float bottomRightX = alignmentPattern.getX();
		final float bottomRightY = alignmentPattern.getY();
		final float sourceBottomRightX = dimMinusThree - 3.0f;
		final float sourceBottomRightY = sourceBottomRightX;

		final float s = srcSize / dimension;
		final Mat src = new Mat(4, 1, opencv_core.CV_32FC2, new FloatPointer(3.5f * s, 3.5f * s, dimMinusThree * s,
				3.5f * s, sourceBottomRightX * s, sourceBottomRightY * s, 3.5f * s, dimMinusThree * s));
		final Mat dst = new Mat(4, 1, opencv_core.CV_32FC2, new FloatPointer(topLeft.getX(), topLeft.getY(),
				topRight.getX(), topRight.getY(), bottomRightX, bottomRightY, bottomLeft.getX(), bottomLeft.getY()));
		return opencv_calib3d.findHomography(src, dst);
	}
}
