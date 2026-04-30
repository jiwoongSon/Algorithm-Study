#  0430 타겟 넘버

## 풀이1
예외값이 없다. 무조건 numbers 배열의 조합으로 target이 만들어지는 방식

더하거나 빼는 방식만이 존재. 이증 for문을 이용한 재귀함수로 진행?? 

 + + + ... 에서 시작 (default 는 다 양수인 경우)
 일단 로직은 다 더하는거
 순서대로 모든경우, nP1, nP2 ... nPn까지 진행해야 한다. 여기서 뒤의 원소의 개수는 마이너스를 적용하는 숫자의 개수

마이너스를 어떻게 적용?? n-1개를 일단 고정시키고 나머지 하나를 돌리기. 이때 순서는 인덱스가 작은 것 부터

ex) 7P3

012
013
014
015
016

023 -> 인덱스를 하나 뒤로 밀기, 022 방지
024
025 
026

...

123 -> 마찬가지로 인덱스를 하나 밀기


---

n1 P n2 
n1은 -를 붙이는 수의 개수
n2는 

```java
import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;

        int sum1 = 0;
        for(int i = 0; i<numbers.length; i++) {
                sum1 += numbers[i];
        }
        if(sum1 == target) return 1; // 전부 다 양수로 더해서 target값과 같게 나오는 경우

    
        for(int i=1; i<numbers.length; i++) {
            if(plusMinus(numbers, target, i, 0)) answer++; // i개의 마이너스 부호를 가진다. 
        }

        return answer;

        


    }

    public boolean plusMinus(int[] numbers, int target, int n, int m) {

        if(sum == target) return true;

        for(int i = 1; i<=n; n++) {
            m = m + i;
            numbers[m] *= -1;
        }

        int sum = 0;
        for(int j = 0; j<numbers.length; j++) {
            sum += numbers[j];
        }

        m
        m+1
        m+2
        .. 총 n개의 인덱스를 -부호로 하고 더하기
        
    }
}
```

코드짜다가 실패

## 풀이 2(정답)
각 숫자마다 더하기나 빼기 둘중애 하나만 하면 됨. 이항정리!

```java
import java.util.*;
class Solution {
    int answer = 0; 

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    public void dfs(int[] numbers, int target, int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        
        dfs(numbers, target, index + 1, sum + numbers[index]);
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}
```
## 생각해볼 점
sum을 꺼내놓고 생각. 
일단 깊이 우선이라는걸 한번은 떠올려보자.

## 개선할 점(코드)
## 성능