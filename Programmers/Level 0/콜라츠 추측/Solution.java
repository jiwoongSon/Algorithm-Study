public class Solution {
    public int solution(int num) {
        int cnt = 0;
        return collatzCon((long)num, cnt);
        }
    
    
    public int collatzCon(long num, int cnt){

        if(num == 1) return cnt;
        if(cnt>=500) return -1;

        if( num%2 == 0 ) {
            num= num / 2;
            cnt++;
            return collatzCon(num,cnt);
        }   
        else {
            num = num*3 + 1;
            cnt++;
            return collatzCon(num,cnt);
        }
        
    }
}