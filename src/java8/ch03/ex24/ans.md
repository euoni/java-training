入れ子になった`Pair<Pair<T>>`は全体として4個の`T`を持っているが、
`Pair<T>`は2個の`T`しか格納できないので`flatMap`操作は実現できない。
