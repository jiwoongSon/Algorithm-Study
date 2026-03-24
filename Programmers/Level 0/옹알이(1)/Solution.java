import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] can = {"aya", "ye", "woo", "ma"};
        
        for (String s : babbling) {
            for (String c : can) {

                s = s.replace(c, " ");
            }
            

            if (s.trim().length() == 0) {
                answer++;
            }
        }
        return answer;
    }
}