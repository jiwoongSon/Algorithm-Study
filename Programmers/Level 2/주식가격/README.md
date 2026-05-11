#  0508 주식가격
## 풀이
- Stack 자료구조 사용
- 문제에서 최종 return하는 자료 -> 시간
    - 시간 기준으로 자료구조 작성

- Monotonic Stack : 단조 스택 
    - 단일 방향으로만 증가하거나 감소하는 성질
    - 이중 반복문을 써서, 검사하면 O(N^2)
        - 하지만 스택을 쓰면 O(N) 이다. 

```java
class Solution {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                answer[i]++; 
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }
        return answer;
    }
}
```


## 생각해볼 점
- Java에서 스택 사용
    - 직접 구현 -> 넉넉한 size의 배열로, pointer 증감연산을 통해 구현 
        - Solution class 안에 Inner class 만들어도 되고, 그냥 배열 선언해도 됨. 
    - 내부 라이브러리
        - Stack<Integer> stack = new Stack<>();
        - Deque<Integer> stack = new ArrayDeque<>();



- Monotonic Stack 이외의 Stack 종류
    - Standard Stack : 일반 LIFO
    - Min Max Stack
        - 내부적으로 stack 2개를 둔다.
            - 하나는 Data Stack
            - 하나는 Min Value Stack
    - CallStack
        - Recursive하게 구현


## Java 내부 Stack과 Deque의 차이 
- Stack Class : java 1.0 시절에 만들어짐.
    - 따라서 synchronized가 걸려있다. 
        - Single Thread 환경에서도 Lock을 걸었다가 푸는 불필요한 Overhead 사용
    - 내부적으로 Vector Class를 상속받음. 
        - Vector가 가진 array 접근 메서드가 외부에 노출됨. 
        - stack.get(indexNum) 으로 스택 중간의 item을 삭제/삽입 가능하다. 이럼 안되지. 

- Deque(ArrayDeque) : java 6 때 만들어짐
    - Single Thread 환경에서 Stack Class 보다 빠르다. 
    - Multi Thread 환경? ConcurrentLinkedDeque 사용

- Thread Synchronization?: 멀티쓰레드 환경에서 Shared Memory에 대한 Lock 설정
    - Critical Section: 공유 메모리에 접근하는 실제 코드 구간
    - Lock / Mutex / Mutual Exclusion : 자물쇠 도구


## 개선할 점(코드)
## 성능