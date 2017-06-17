Collectionを実装しているクラスにすでに同名のメソッドがあった場合、新しいjavacでコンパイルが失敗する。

例えば、以下のコードはJava7ではコンパイルできるが、Java8ではエラーとなる。

	class CollectionImpl<E> implements Collection<E> {
		public void stream() {}

		// 以下、Collectionの抽象メソッドの実装
	}

Java7でコンパイルした上記クラスのバイトコードをJava8でロードして`stream()`を呼ぶ場合、上記で定義した`stream()`が呼ばれる。
