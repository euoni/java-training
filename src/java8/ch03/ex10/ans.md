`compose`は`Function`を返すので、`UnaryOperator`に変換しないと使用できない。
ストラクチャル型ならば、`Function<T,T>`と`UnaryOperator<T>`は同じ型とみなせるので変換なしに用いることができる。
