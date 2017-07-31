ドキュメントでは`reduce`の等価コードとして以下が示されている。

```
U result = identity;
     for (T element : this stream)
         result = accumulator.apply(result, element)
     return result;
```

`result`は`U`型なので、`accumulator`の第一引数の型は`U`のままでよい。
