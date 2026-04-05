import java.util.*;

class Solution {

    public int bfs(int startR, int startC, int n, int m, char[][] matrix, boolean[][] visited) {
        Queue<int[]> que = new LinkedList<>();

        int[] dr = {-1, 1, 0 ,0};
        int[] dc = {0, 0, 1, -1};

        que.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        int sum = matrix[startR][startC] -'0';

        while(!que.isEmpty()) {
            int[] current = que.poll();
            int r = current[0];
            int c = current[1];
            
            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(matrix[nr][nc] == 'X') continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true; 
                que.offer(new int[]{nr, nc}); 
                
                sum += matrix[nr][nc] - '0'; 
            }
        }
        return sum;
    }


    public int[] solution(String[] maps) {

        int n = maps.length;
        int m = maps[0].length();
        char[][] matrix = new char[n][m];

        boolean[][] visited = new boolean[n][m];


        List<Integer> land = new ArrayList<>();

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                matrix[i][j] = maps[i].charAt(j);
            }
        }

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(matrix[i][j] != 'X' && !visited[i][j]) {
                    int sum = bfs(i,j,n,m,matrix,visited);
                    land.add(sum);
                }
            }
        }

        if (land.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(land); // 오름차순 정렬

        int[] answer = new int[land.size()];
        for (int i = 0; i < land.size(); i++) {
            answer[i] = land.get(i);
        }
        return answer;
    }
}

