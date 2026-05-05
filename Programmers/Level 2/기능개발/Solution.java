import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i<progresses.length; i++) {
            int remainTime = 100 - progresses[i];
            int remainDay = (int) Math.ceil((double) remainTime / speeds[i]);
            queue.offer(remainDay);
        }
        // progresses랑 speeds를 이용하여 남은 일 수 계산 -> queue에 offer

        List<Integer> answerList = new ArrayList<>();

        int currentDay = queue.poll(); // front에서 하나 뽑아서 
        int cnt = 1; // 같이 묶어서 내보낼 개수. default는 1. 하나씩 내보내야 하므로


        while(!queue.isEmpty()) {
            int nextDay = queue.poll();

            if(nextDay <= currentDay) cnt ++; 
            // 기다려야 되는 경우

            else {
            // 뒤의 기능이 더 긴 경우

                answerList.add(cnt); 
                cnt = 1; 
                currentDay = nextDay; 

            }
        }

        answerList.add(cnt);
        return answerList.stream().mapToInt(i -> i).toArray();
        // dynamic size를 위해 List 사용 -> array로 바꿔야됨. 
        
    }
}