import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> myQueue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < priorities.length; i++) {
            myQueue.add(new int[]{priorities[i], i}); 
            pq.add(priorities[i]);
        }

        int answer = 0;

        while(!myQueue.isEmpty()) {
            int maxValue = pq.peek();
            int[] current = myQueue.peek(); 

            if(maxValue == current[0]) {
                myQueue.remove();
                pq.remove();
                answer++; 
                
                if(current[1] == location) {
                    return answer; 
                }
            } 
            else {
                myQueue.add(myQueue.peek()); 
                myQueue.remove();
            }
        }
        
        return answer;
    }
}