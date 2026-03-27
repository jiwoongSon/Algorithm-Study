#  0327 완주하지 못한 선수
## 풀이
1. 이중 for문을 이용한 풀이
```java
class Solution {
    public String solution(String[] participant, String[] completion) {
        for (int i = 0; i < participant.length; i++) {
            boolean isCompleted = false; // flag
            for (int j = 0; j < completion.length; j++) {
                if (participant[i].equals(completion[j])) {
                    isCompleted = true; 

                    completion[j] = ""; 
                    
                    break; 
                }
            }
            
            if (!isCompleted) {
                return participant[i]; 
            }
        }
        
        return "";
    }
}
```
String의 equals 메서드를 이용하여 어렵지 않게 풀이할 수 있다. 하지만 N^2의 시간이 걸림. 

2. HashMap을 이용한 풀이
```java
import java.util.*;
 
class Solution {

    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> map = new HashMap<>();

        for(String a : participant) {
            map.put(a, map.getOrDefault(a, 0) + 1); // flag에 0+1을 넣음. 동명이인이 있다면 (기존값) + 1
        }

        for(String a : completion) {
            map.put(a, map.get(a)-1); 
            //완주했다면, 기존의 flag(이름별 참가인원수) - 1
        }

        for(String a : map.keySet()) {
            if(map.get(a) != 0) answer = a;
        }


        return answer;
    }
}
```


## 생각해볼 점
- Map? : (Key, Value)로 저장하는 자료구조
    - Key는 절대 중복될 수 없다. 
    - Value는 중복되어도 상관 없다. 
    - hashmap: 마법의 안내 데스크. 키값을 보고 벨류의 위치를 바로 알아낼 수 있다. 
    - put(Key, Value)
    - get(key): Value return
    - containKey(Key):  해당 key가 존재하는지 O(1) 속도로 확인. return true/false
    - GetOrDefaultKey(Key, DefaultValue): 해당 키가 있으면 원래 있던 value를 리턴, 없다면 기본값 리턴. Couting Logic을 짤 때 중요하다. 
    - entrySet() : 모든 key-value을 가져옴
    - keySey() : 모든 key를 가져옴
## 개선할 점(코드)
- 람다와 스트림
## 성능
O(N)
