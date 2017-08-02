```
$ find src -name "*Filter.java" | xargs grep -l @FunctionalInterface
src/java/io/FileFilter.java
src/java/io/FilenameFilter.java
src/java/util/logging/Filter.java

```

全て`Predicate<T>`で置き換え可能である。
