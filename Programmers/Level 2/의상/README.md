#  0419 의상

## 풀이 1
- 조합 문제 -> 착용해야 하는 의상의 min 값이 없으니, 1개의 종류만 착용한 경우 + 2개의 의상만 착용한 경우 + ... + n개의 의상 모두를 착용한 경우 

의상의 총 개수가 t개라고 하면, clothes는 t*2 의 2차원 배열의 형태를 띄게 된다. 
의상의 종류: n개
의상의 총 개수: t개 

- 1개의 의상만 착용한 경우: t
- 2개의 의상을 착용한 경우: tC2 -> t * (t-1) / 2
- 3개의 의상을 착용한 경우: tC3 -> 
 ...
- n개의 의상을 착용한 경우: tCn
for문을 이용하여 Combination을 구현 한 뒤, t번 반복

nC0 + nC1 + ... + nCn = 2^n 이지만, 해당 문제에서는 t == n 이라는 보장이 없다.

결정적으로, 전체를 기준으로 개수를 세다 보면 같은 종류의 의상을 입는 경우가 생김 --> Loop에 의상의 종류 배열과 비교해서 같지 않은것들만 추가? --> MAX_VALUE 30, 즉 의상이 종류별로 하나씩만 있고 종류가 30개인 경우 2^30 - 1 연산 --> Overhead가 너무 커짐 -> TimeOut 

```java
import java.util.*;

class Solution {
    int answer = 0;

    public int solution(String[][] clothes) {
        boolean[] visited = new boolean[clothes.length];
        combination(clothes, visited, 0, 0);
        
        return answer;
    }

    private void combination(String[][] clothes, boolean[] visited, int start, int depth) {
        if (depth > 0) {
            if (isValid(clothes, visited)) {
                answer++; 
            }
        }

        for (int i = start; i < clothes.length; i++) {
            visited[i] = true;
            combination(clothes, visited, i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private boolean isValid(String[][] clothes, boolean[] visited) {
        Set<String> typeSet = new HashSet<>();
        int selectedCount = 0;

        for (int i = 0; i < clothes.length; i++) {
            if (visited[i]) {
                typeSet.add(clothes[i][1]); 
                selectedCount++;            
            }
        }
        return typeSet.size() == selectedCount;
    }
}
```


---
## 풀이 2
모든 경우의 수를 출력하는게 아니라 개수만 찾으면 됨.

각 Clothes 배열의 원소 [옷의 이름, 옷의 종류] 를 HashMap에 저장. <옷의 이름, 옷의 개수>

```java
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1; 
        
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        for (int count : map.values()) {
            answer *= (count + 1);
        }
        return answer - 1; // 아무것도 안 입은 경우
    }
}
```






## 생각해볼 점
- String "내용" 검사 : equals()
    - == 는 객체의 주소를 비교. 

- Hash
    - 단방향 암호화 기법 
    - 본질은, 무언가를 변환. input x -> Hash Function -> **일정한 길이**의 Output y

- 자료구조에서의 Hash
    - Output을 데이터의 index로 사용
    - HashMap
        - Key, Value
        - Pair로 움직임
        - 풀이 2에서, 개수만 알면 되니까 <옷의 종류, 옷의 개수> 로 사용
        - N * 2 배열? 
            - 다른 자료형도 가능
            - O(1)의 연산속도 <- Hash Function
            

    - HashSet
        - Map의 Key 집합
        - 위의 풀이 1에서, 중복 검사를 할 때 사용 가능
            - 뽑은 옷의 개수와 HashSet의 size()를 비교하면 된다. 
        - Java에서의 set은 interface
            - Set<String> mySet = new HashSet<>();


- 풀이 1에서의 backtracking
    - A,B,C 중에서 2개를 골라보자
        - AB, AC, BC
        - 여기서는 visited[]로 확인한다. 따라서 초기 상태 F, F, F에서 A를 고르고 (T, F, F) -> B를 고르면 (T, T, F) : AB 조합이 완성된다.
        - 이제 AC를 찾아야 하는데, 
```java
for (int i = start; i < clothes.length; i++) {
    visited[i] = true;  // [1] 옷을 입는다.
    
    // [2] 이 옷을 입은 상태에서 파생되는 모든 경우의 수를 탐색하러 떠난다!
    // (그리고 이 탐색이 전부 끝날 때까지 아래 코드로 안 넘어감)
    combination(clothes, visited, i + 1, depth + 1); 
    
    visited[i] = false; // [3] 탐색을 전부 마치고 돌아오면, 비로소 옷을 벗는다.
}
```
## 개선할 점(코드)
## 성능