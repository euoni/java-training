import sys

for line in sys.stdin:
  for x in line:
    sys.stdout.write('\u{:04x}'.format(ord(x)))
