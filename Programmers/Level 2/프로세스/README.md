#  0507 프로세스

## 풀이
**별도의 언급이 없으므로, 초기 상황에서는 index 0 요소부터 큐에서 꺼낸다고 생각하면 된다.**

### 풀이 1
```java
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Integer> myQueue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<priorities.length; i++) {
            myQueue.add(priorities[i]);
            pq.add(priorities[i]);
        }

        int answer = 0;
        int cntIndex = 0;

        while(!myQueue.isEmpty()) {
            int maxValue = pq.peek();
            if(maxValue == myQueue.peek()) {
                myQueue.remove();
                pq.remove();
                if(cntIndex == location) answer = cntIndex;
                cntIndex++;
            }

            else {
                myQueue.add(myQueue.peek());
                myQueue.remove();
                cntIndex++;
            }
        }
        return answer;

    }
}
```
그럴듯하게 작성했지만 틀렸다. 
- 문제점 : while문 한번 돌면 -> 다음꺼 큐에 있던 검사하니까 cntIndex가 결국 원래 인덱스를 나타낸다고 생각함. 
    - 아님. n번 회전한다고 무조건 다 출력될수가 없다. 애초에 두바퀴 세바퀴 돌 수도 있으니까

### 풀이 2
- 그냥 원소를 넣을 때, 인덱스도 같이 박아버리면 된다. 큐를 Int가 아니라 Int[] 로 만들어버리면 된다. 

```java
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> myQueue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < priorities.length; i++) {
            myQueue.add(new int[]{priorities[i], i}); 
            pq.add(priorities[i]);
        }

        int answer = 0;

        while(!myQueue.isEmpty()) {
            int maxValue = pq.peek();
            int[] current = myQueue.peek(); 

            if(maxValue == current[0]) {
                myQueue.remove();
                pq.remove();
                answer++; 
                
                if(current[1] == location) {
                    return answer; 
                }
            } 
            else {
                myQueue.add(myQueue.peek()); 
                myQueue.remove();
            }
        }
        
        return answer;
    }
}
```

## 생각해볼 점
- 자바 큐의 최댓값 구하는 방법
    - Collections.reverseOrder() 해서 큐 하나 만들고 젤 위에꺼 peek()
    - 우선순위 큐에 대한 내용은 이전에 다루었다. 
## 개선할 점(코드)
## 성능