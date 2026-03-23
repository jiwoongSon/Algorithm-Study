class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(n>s) return new int[]{-1};

        if(s%n== 0) {
            for(int i = 0; i<n; i++){
                answer[i] = s/n;
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                answer[i] = s/n;
            }
            
            int cnt = s%n;
            for (int i = 0; i < n; i++) {
                answer[n - 1 - i] += 1;
                cnt -= 1;
                if (cnt == 0) break; 
            }
        }  
        return answer;
        
        
    }
}