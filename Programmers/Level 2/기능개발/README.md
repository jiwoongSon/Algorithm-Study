#  0505 기능 개발

## 풀이
## 생각해볼 점
- Queue
    - FIFO 구조, java에서는 LinkedList로 구현한다. 
        - En Queue : 앞에서 data 추가 
        - De Queue : 앞에서 data 삭제
    - 한쪽을 Front로, 한쪽을 Rear로 각각 삽입, 삭제 연산만 수행
    - BFS에서 사용 
    - peek, pop, push .. 등의 메서드 사용

- Math Library
    - max(a,b), min(a,b) -> 중첩 사용도 가능
        - parameter는 2개씩만
    - abs(a) 
    - ceil((double) a) : 아예 올림
    - floor((double) a) : 아예 내림
    - round((double) a) : 반올림
    - integer간의 계산은 자동으로 버림 -> double로 명시적 형변환 이후 함수에 인자로 넘겨주자


```java
        return answerList.stream().mapToInt(i -> i).toArray();
```

- Method Chaining
    - stream() : stream<Integer> Object return
    - mapToPoint(i->i) : IntStream Object return
    - ToArray() : int[] return 

## 개선할 점(코드)
## 성능