import java.util.*;

public class Solution {
    public int solution(String numbers) {

        
        int n = numbers.length();
        Set<Integer> numSet = new HashSet<>();
        Set<Integer> primeSet = new HashSet<>();

        String currentStr = "";
        boolean[] visited = new boolean[n];

        makeNum(currentStr, numbers, visited, numSet);

        for(int num : numSet) {
            if(primeNum(num)) primeSet.add(num);
        }

        return primeSet.size();
    }


    private boolean primeNum(int number) {

        if(number == 0 || number == 1) return false;
        
            for(int i = 2; i<number; i++) {
                if(number%i == 0) return false;
            }

        return true;
    }


    private void makeNum(String currentStr, String numbers, boolean[] visited, Set<Integer> numSet) {

        if(!currentStr.equals("")) {
            numSet.add(Integer.parseInt(currentStr));
        }

        for(int i=0; i<numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                makeNum(currentStr + numbers.charAt(i), numbers, visited, numSet);
                visited[i] = false;
            }
        }
    }
    
}
