# jpl.ch04.ex05

## (a) N分木のノードを表す`TreeNode`
インターフェース

N個の子をどのように格納し扱うかは実装したクラスによって異なるのでインターフェース。

## (b) 特定の順番（深さ優先、幅優先）で木を探索する`TreeWalker`
抽象クラス

探索の実装は抽象クラスに、探索したノードに対する処理はサブクラスの具象クラスで実装する。

## (c) グラフィックシステムにより描画可能なオブジェクトのための`Drawable`
インターフェース

何らかのクラスのサブクラスでもDrawableとするためにインターフェース。

## (d) グラフィックデスクトップから実行できるプログラムのための`Application`
具象クラス

特定環境向けの具体的な実装を伴うので具象クラス。
