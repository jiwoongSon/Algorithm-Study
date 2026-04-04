import java.util.*;

class Solution {

    public int bfs(int StartR, int startC, int n, int m, char[][] matrix, boolean[][] visited) {
        
    }

        int[] dr = {-1, 1, 0 ,0};
        int[] dc = {0, 0, 1, -1};

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

                }
            }
        }


    }
}

