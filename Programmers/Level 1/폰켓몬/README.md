# 0326 폰켓몬 
## 풀이
같은 종류(번호)의 포켓몬을 하나의 포켓몬으로 인정 -> 배열을 집합으로 변환, 또는 중복요소 하나로
그리고 조합 연산 진행

```java
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int n = nums.length();
        
        Set<Integer> nums = new Set<Integer>(Arrays.asList(nums));
        int uniqueSize = nums.size();
        if(n/2 <= uniqueSize) answer = n/2;
        else answer = uniqueSize;
        
        return answer;
    }
}
```
문법적 오류가 많다.

- 배열의 길이는 속성이다. 즉 괄호 없이 그냥 반환 
- 변수명이 겹친다. 그리고 Set을 생성할땐 hashset으로 생성
- Arrays.asList(nums) 부분
    - 객체 배열에서만 리스트로 정상 변환된다. 기본형 배열을 넣었을 때, 배열 안의 숫자들을 빼서 List로 변환하는것이 아니라 array 자체를 하나의 객체로 보고 List로 변환한다. 따라서 타입 에러가 발생한다. 

```java
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int n = nums.length; 
        
        Set<Integer> uniqueNums = new HashSet<>();
        
        for (int num : nums) {
            uniqueNums.add(num);
        }
        
        int uniqueSize = uniqueNums.size();

        if(n/2 <= uniqueSize) answer = n/2;
        else answer = uniqueSize;
        
        return answer;

    }
}
```

## 생각해볼 점
- HashSet?
    - Set은 interface
    - HashSet은 class
        - 데이터를 셋에 넣을 때, 해시 함수를 이용하여 인덱스로 변환해줌. 
        - 최악의 경우(Worst Case)에는 O(N)까지 성능이 떨어질 수 있지만, 자바 내부적으로 최적화가 잘 되어 있어 실무와 코딩 테스트 환경에서는 평균적으로 O(1)
        - TreeSet, LinkedHashSet ..
        - 데이터의 중복을 검사 할 때, 배열처럼 모든 인덱스를 방문하는게 아니라 hash함수로 중복검사
        - 1이 어디에 들어있는지 알고싶을 때, 배열의 경우 모든 인덱스를 전부 방문해야됨. hash의 경우 특정 함수를 이용하여 1이 어디에 있는지 바로 알 수 있다. 

## 개선할 점(코드)
- return Math.min(n / 2, uniqueSize);
    - 코드 한 줄로 좀 더 깔끔하게 작성할 수 있다. 
## 성능
O(n)