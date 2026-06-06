#  0603 큰 수 만들기
## 풀이
- 문자열 형식으로 주어진 String의 "순서를 바꾸지 않고" 만들 수 있는 가장 큰 수
- StringBuilder 이용하여 앞자리에 가장 큰 수를 오게끔 한다. 
- 이후 for문으로 각 자리를 검사
    - 스택에 숫자들이 존재 & 남은 횟수 존재 & 이전 자리의 수가 지금의 수보다 작은 경우에만 --> greedy 하게 지우고 다시
    - 만약 지금 들어온 숫자가 더 크다면, 원래 있던 숫자를 지우고 k--, 그렇게 해서 일단 현재 자리에는 가장 큰수 를 넣을 수 있게끔 한다. 


## 생각해볼 점
- String Bulider
    - 내부에 char array를 가지고 있음. 
    - 문자열의 추가와 삭제가 자주 일어날 때 속도향상

- 문제에서 "지우는 횟수 : k" 라고 준 이유가 있을 듯 하다. 
    - 그리디 알고리즘 가이드라인. 
    - 보통은 n자리 자연수를 만들라고 하지 않나? 
    - 그리고 Input range를 보면, 백만자리이다. 
    - DFS/backtracking의 경우에는 15-20자리 내외로 아주 작게 주어짐. 
        - 모든 경우의 수를 다 따져야 하기 때문에, 2^N 의 시간 복잡도를 가짐. 
    - time limit가 넉넉하다면 아래처럼 모든 경우의 수를 다 찾을 수 있음. 

```java
class Solution {
    String maxNumber = "0";

    public String solution(String number, int k) {
        int targetLength = number.length() - k;
        StringBuilder current = new StringBuilder();
        
        findMaxByCombination(number, targetLength, 0, current);
        
        return maxNumber;
    }

    private void findMaxByCombination(String number, int targetLength, int index, StringBuilder current) {
        
        if (current.length() == targetLength) {
            // 탈출조건
            if (current.toString().compareTo(maxNumber) > 0) {
                maxNumber = current.toString();
            }
            return;
        }

        for (int i = index; i < number.length(); i++) {
            current.append(number.charAt(i)); 
            findMaxByCombination(number, targetLength, i + 1, current);
            current.deleteCharAt(current.length() - 1); 
        }
    }
}
```

## 개선할 점(코드)
## 성능