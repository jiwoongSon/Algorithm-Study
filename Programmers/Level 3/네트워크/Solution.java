class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                dfs(i, computers, visited);
                answer++; 
            }
        }

        return answer;
    }

    private void dfs(int node, int[][] computers, boolean[] visited) {
        visited[node] = true;

        for (int i = 0; i < computers.length; i++) {
            if (node != i && computers[node][i] == 1 && visited[i] == false) {
                dfs(i, computers, visited);
            }
        }
    }
}