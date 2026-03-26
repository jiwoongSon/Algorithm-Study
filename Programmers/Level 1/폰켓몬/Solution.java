import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int n = nums.length; 
        
        Set<Integer> uniqueNums = new HashSet<>();
        
        for (int num : nums) {
            uniqueNums.add(num);
        }
        
        int uniqueSize = uniqueNums.size();
        
        return Math.min(n / 2, uniqueSize);
    }
}