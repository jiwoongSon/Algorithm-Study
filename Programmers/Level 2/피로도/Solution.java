import java.util.*;

class Solution {
    int answer; 

    public int solution(int k, int[][] dungeons) {
        answer = 0; 
        boolean isVisited[] = new boolean[dungeons.length];
        
        for(int firstIndex = 0; firstIndex < dungeons.length; firstIndex++) {
            // 1. 부등호 수정: 같아도(<=) 입장 가능!
            if(dungeons[firstIndex][0] <= k) {
                dfs(k, dungeons, firstIndex, 1, isVisited);
            }
        }

        return answer;
    }

    // 파라미터에 int count 추가
    public void dfs(int k, int[][] dungeons, int index, int count, boolean[] isVisited) {        
        
        isVisited[index] = true;
        k -= dungeons[index][1];

        answer = Math.max(answer, count);

        for(int i = 0; i < dungeons.length; i++) {
            if(!isVisited[i] && k >= dungeons[i][0]) {
                dfs(k, dungeons, i, count + 1, isVisited);
            }
        }
        
        isVisited[index] = false; 
    }
}