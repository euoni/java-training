# 第一引数にインスタンスを受け取るメソッド(メソッドの説明に「指定された」とあるメソッド)
第一引数のインターフェースにデフォルトメソッドとして実装する。

例：

1. `Collections.addAll(Collection<? super T> c, T... elements)` -> `Collection.addAll(T... elements)`
2. `Collections.fill(List<? super T> list, T obj)` -> `List.fill(T obj)`

# それ以外のメソッド
返り値のインターフェースにスタティックメソッドとして実装する。

例：

1. `Collections.emptyList()` -> `List.empty()`
2. `Collections.reverseOrder()` -> `Comparator.reverseOrder()`
