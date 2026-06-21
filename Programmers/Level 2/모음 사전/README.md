#  0621 모음사전

## 풀이
- 각 자리에는 "가중치" 존재 
- 각 자리의 가중치(사전에서 몇 단위로 움직이는지)를 알면 모든 경우의 수를 구하지 않아도 된다. 
- 가중치는 다음과 같다. 
    - 자기 자신이 들어감 -> + 1 (뒤에 아무것도 오지 않는 경우)
    - 인덱스마다 5개의 원소가 들어갈 수 있음. + 5^n
    - 각 자리마다, 5^n + (이전 자리수의 누적) * (알파벳의 번호) + 1
        - 내 sub로 올 수 있는 모든 경우의 수 + 1


## 생각해볼 점
- DFS로도 풀 수 있음. 
```java
import java.util.*;

class Solution {
    // 만들어진 모든 단어를 저장할 리스트
    List<String> dict = new ArrayList<>();
    String[] vowels = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        // 빈 문자열부터 시작하여 모든 경우의 수를 재귀적으로 생성합니다.
        dfs("", 0);
        
        // 리스트는 0번 인덱스부터 시작하므로 +1을 해줍니다.
        return dict.indexOf(word) + 1;
    }
    
    private void dfs(String str, int depth) {
        // 기저 조건: 길이가 5를 초과하면 더 이상 탐색하지 않고 종료합니다.
        if (depth > 5) {
            return;
        }
        
        // 빈 문자열이 아니라면 사전에 단어를 등록합니다.
        if (!str.isEmpty()) {
            dict.add(str);
        }
        
        // 5개의 모음을 순서대로 붙여가며 다음 깊이로 내려갑니다.
        for (int i = 0; i < 5; i++) {
            dfs(str + vowels[i], depth + 1);
        }
    }
}
```
- indexOf(Object o) -> 해당 값은 몇 번째 자리에 있냐? 

- BFS로도 모든 경우의 수를 만들어서 풀 수 있음. 사전순서이므로, sort method를 사용하면 된다. 
## 개선할 점(코드)
## 성능