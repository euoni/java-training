# Result
| Environment                                                                                     | Deadlock |
| ----------------------------------------------------------------------------------------------- | --------:|
| Win10 Pro x64 / Intel Core i5-4690 / Oracle Java 1.8.0_121                                      | 62 / 100 |
| Win10 Home x32 / Intel Atom Z3740 / Oracle Java 1.8.0_111                                       | 92 / 100 |
| Docker openjdk:8u111-jre-alpine on Ubuntu 16.04.2 x64 / Intel Celeron J3160 / OpenJDK 1.8.0_111 | 24 / 100 |

# yield
例えばFriendlyクラス内でyieldを呼んだとして、Thread1のFriendlyインスタンスがyieldを読んでThread2へ処理時間を譲ったとしても、Thread2のFriendlyインスタンスもまたyieldでThread1へ譲ってしまうので、デッドロックを防げない。
