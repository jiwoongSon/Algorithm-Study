import java.util.*;

class Solution {

    int[] dx  = {-1, 1, 0, 0}; //상하
    int[] dy = {0, 0, -1, 1}; //좌우
    // i = 0 -> 좌
    // i = 1 -> 우
    // i = 2 -> 상
    // i = 3 -> 하


    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m]; //방문 여부 저장 배열
        Queue<int[]> que = new LinkedList<>(); //{좌표, 지금까지 이동거리}

        que.offer(new int[]{0,0,1}); // 현재 위치와 이동거리 저장
        visited[0][0] = true; // 현재 위치는 방문함

        while(!que.isEmpty()) {
            int[] current = que.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if(x==(n-1) && y==(m-1)) return dist;
            //목적지에 도달한 경우 현재까지의 거리 return

            for(int i=0; i<4; i++) {
                int nextx = x + dx[i];
                int nexty = x + dy[i];

                if(nextx<0 || nextx>=n-1 || nexty<0 || nexty>m-1) continue;
                if(maps[nextx][nexty] == 0) continue;
                if(visited[nextx][nexty]) continue;

                visited[nextx][nexty] = true;
                que.offer(new int[]{nextx, nexty, dist + 1});
            }            
        }
        return -1;
    }
}