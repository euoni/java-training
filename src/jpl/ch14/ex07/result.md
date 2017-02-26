# Result
| Environment                                                                                     | w/o yield     | w/ yield         |
| ----------------------------------------------------------------------------------------------- | ------------- | ---------------- |
| Win10 Pro x64 / Intel Core i5-4690 / Oracle Java 1.8.0_121                                      | 1, 2, 3, 4, 5 | 1, 2, 3, 4, 5    |
| Win10 Home x32 / Intel Atom Z3740 / Oracle Java 1.8.0_111                                       | 1, 2, 3, 5, 6 | 1, 2, 3, 4, 5, 6 |
| Docker openjdk:8u111-jre-alpine on Ubuntu 16.04.2 x64 / Intel Celeron J3160 / OpenJDK 1.8.0_111 | 1, 2          | 1, 2, 3, 4, 5, 6 |

# Pattern
## Pattern 1
```
Did
Did
DidNot
DidNot
```

## Pattern 2
```
DidNot
DidNot
Did
Did
```

## Pattern 3
```
Did
DidNot
Did
DidNot
```

## Pattern 4
```
DidNot
Did
DidNot
Did
```

## Pattern 5
```
Did
DidNot
DidNot
Did
```

## Pattern 6
```
DidNot
Did
Did
DidNot
```
