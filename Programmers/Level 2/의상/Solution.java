import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1; 
        
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];
            map.put(type, map.getOrDefault(type, 0) + 1);
            // getOrDefault((KEY), DEFAULT) : KEY의 VALUE를 return
            // put((KEY), (VALUE)) : map에 put, 또는 덮어쓰기
        }


        for (int count : map.values()) {
            answer *= (count + 1);
        }
        return answer - 1; // 아무것도 안 입은 경우
    }
}