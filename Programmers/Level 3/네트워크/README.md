#  0501 네트워크

## 풀이
dfs -> stack (Recursiv function)
완전탐색: 모든 경우의 수 탐색
경로의 특징을 기억해야 할 때

bfs -> queue 사용 
최단거리


두가지 방법으로 구현 가능

## 생각해볼 점
BFS : 내 주변 기준 가까운 곳부터 --> 가장 먼저 발견해서 대기열에 넣고, 해당 element를 가장 먼저 꺼내서 탐색. queue(linkedList로 구현)

```java
Queue<Integer> queue = new LinkedList<>();
```
## 개선할 점(코드)
```java
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                bfs(i, n, computers, visited);
                answer++;
            }
        }

        return answer;
    }

    private void bfs(int startNode, int n, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(startNode);

        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int i = 0; i < n; i++) {
                if (node != i && computers[node][i] == 1 && visited[i] == false) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
```
## 성능