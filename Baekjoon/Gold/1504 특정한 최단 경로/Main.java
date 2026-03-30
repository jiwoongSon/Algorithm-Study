import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<List<int[]>> graph;
    
    static final int INF = 200000000; // 각각의 경로를 더해야 함. MAX_VALUE를 사용하면 overflow
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine()); 
            int source = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
    
            // 양방향 graph 포스트잇 붙이기
            graph.get(source).add(new int[]{dest,cost});
            graph.get(dest).add(new int[]{source,cost});
        }

        st = new StringTokenizer(br.readLine()); 
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());


        int path1 = 0;
        path1 += dijkstra(1, v1);
        path1 += dijkstra(v1, v2);
        path1 += dijkstra(v2, N);

        int path2 = 0;
        path2 += dijkstra(1, v2);
        path2 += dijkstra(v2, v1);
        path2 += dijkstra(v1, N);

        int minPath = Math.min(path1, path2);

        if (minPath >= INF) {
            System.out.println("-1");
        } else {
            System.out.println(minPath);
        }
    }


    public static int dijkstra(int start, int end) {
        int[] dist = new int[N + 1]; 
        Arrays.fill(dist, INF);
        dist[start] = 0; 

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curCost = current[1];

            if(dist[curNode] < curCost) continue;

            for(int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextCost = next[1];

                if(curCost + nextCost < dist[nextNode]) {
                    dist[nextNode] = curCost + nextCost;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        
        return dist[end];
    }
}