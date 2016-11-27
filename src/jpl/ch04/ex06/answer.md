# jpl.ch04.ex06

## (a) N分木のノードを表す`TreeNode`
N分木ではなく二分木など特定の構造ならば、具象クラス。

## (b) 特定の順番（深さ優先、幅優先）で木を探索する`TreeWalker`
様々な探索順番を表現する場合は、インターフェース。

## (c) グラフィックシステムにより描画可能なオブジェクトのための`Drawable`
全ての実装クラスで必要になるメソッドがあるならば、抽象クラス。

## (d) グラフィックデスクトップから実行できるプログラムのための`Application`
グラフィックデスクトップだけではなくCUIなど他の環境も含めたアプリケーションを表現するならば、インターフェースまたは抽象クラス。