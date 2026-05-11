import java.util.*;

public class Solution {

    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i<prices.length; i++) {
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int enteredTime = stack.pop();
                answer[enteredTime] = i-enteredTime;
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int enteredTime = stack.pop();
            answer[enteredTime] = ((prices.length) - 1) - enteredTime;
        }

        return answer;
    }    
}
