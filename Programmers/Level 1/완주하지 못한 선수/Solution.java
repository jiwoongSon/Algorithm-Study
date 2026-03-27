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