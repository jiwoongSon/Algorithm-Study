import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> queue = new LinkedList<>();

        int t = 0;
        int w = 0;
        int n = 0;

        for(int i = 0; i<bridge_length; i++) {
            queue.offer(0);
        }

        while(n < truck_weights.length) {
            t ++ ;

            int endTruck = queue.poll();
            w = w - endTruck;

            int nextTruck = truck_weights[n];

            if(w + nextTruck <= weight) {
                queue.offer(nextTruck);
                w = w + nextTruck;
                n ++;
            }
            else {
                queue.offer(0);
            }
        }

        t = t + bridge_length;

        return t;


    }
}