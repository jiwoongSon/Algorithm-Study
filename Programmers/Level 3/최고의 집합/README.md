# 최고의 집합 0323

## 풀이
산술기하평균의 원리에 의해, 곱이 최대가 되려면 해당 수의 중앙값에 위치할수록 곱이 최대가 됨. 

이떄, 값이 n으로 나누어떨어지는 경우엔 해당 값이 최대값이 되고, 나누어떨어지지 않는 경우에는 나누어떨어지는 최대의 수(s/n) 값에 1을 더한 값들이 최고의 집합이 된다. 

---

## 생각해볼 점
- 중앙값에서 2 차이나는 값이 정답이 되는 경우? 
    - 없음. 
- Java 배열 초기화: `new int[n]{-1}`은 불가능. `new int[]{-1}`로 써야 함.

## 개선할 점
- s%n == 0 인 경우와 아닌 경우 모두 s/n 값으로 배열을 초기화 해야됨. 따라서 if else 문을 쓸 필요가 없다. 
- Array.fill() 을 사용하면 for문을 사용하지 않고 배열을 특정 값으로 채울 수 있다.

```java
import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        // 1. 불가능한 경우 예외 처리
        if (n > s) return new int[]{-1};

        int[] answer = new int[n];
        int quotient = s / n;  // 몫
        int remainder = s % n; // 나머지

        // 2. Arrays.fill을 사용하여 한 줄로 초기화 (O(n))
        Arrays.fill(answer, quotient);

        // 3. 나머지만큼 뒤에서부터 1씩 더해줌
        // if-else를 나누지 않아도 나머지가 0이면 이 루프는 돌지 않음
        for (int i = 0; i < remainder; i++) {
            answer[n - 1 - i]++;
        }

        return answer;
    }
}
```

---

## 성능
O(n)
For문