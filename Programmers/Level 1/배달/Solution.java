import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        // 1번 마을 기준, 배달이 가능한지?

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
    
        for (int[] r : road) {
            int a = r[0]; // source
            int b = r[1]; // destination
            int cost = r[2]; // cost

            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }
        /// 1page : {도착 2, 비용 1}
        /// 2page : {도착 1, 비용 1} , {도착 3, 비용 3} ...

        int[] dist = new int[N + 1]; // 마을 번호와 인덱스 번호를 맞추기 위해
        Arrays.fill(dist, Integer.MAX_VALUE); // 일단 무한대로 초기화
        dist[1] = 0; // 시작점(1번 마을) 자기 자신의 거리는 0으로 세팅
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        // 음수면 o1이 더 작음. 자리 바꿀 필요 없다. 양수인 경우 o1이 더 큰 경우이므로 자리 바꾸기
        // 해당 로직을 이용해서 새로운 item이 들어올때마다 queue를 정렬한다. (heap tree)
        pq.offer(new int[]{1, 0}); // 현재 위치는 1, 누적 비용은 당연히 0

        while (!pq.isEmpty()) { 
            int[] current = pq.poll(); // queue에서 1번 마을을 꺼냈음. 
            int curNode = current[0]; // 1
            int curCost = current[1]; // 0

            if (dist[curNode] < curCost) continue;

            for (int[] next : graph.get(curNode)) { // graph.get(1) : index 1(1번 마을)의 curNode get
                int nextNode = next[0]; // 처음엔 2 
                int nextCost = next[1]; // 처음엔 1

                // 기존 장부 값보다 새롭게 거쳐 가는 값이 더 싸다면 덮어쓰기!
                if (curCost + nextCost < dist[nextNode]) {
                    dist[nextNode] = curCost + nextCost;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            // K 시간(비용) 이하로 갈 수 있는 마을이면 정답 +1
            if (dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
}