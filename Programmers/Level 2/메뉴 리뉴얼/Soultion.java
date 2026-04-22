import java.util.*;

class Solution {
    Map<String, Integer> map; // 가능한 모든 조합의 수를 counting할 HashMap

    public String[] solution(String[] orders, int[] course) {
        List<String> answerList = new ArrayList<>();

        for(int i = 0; i< orders.length; i++) {
            char[] arr = orders[i].toCharArray(); // 각 손님의 주문을 char로 변환해서 arr에
            Arrays.sort(arr); // 오름차순 저열
            orders[i] = String.valueOf(arr); // 다시 String으로 바꿔서 원래 배열에 저장
        }

        for(int c : course) {
            map = new HashMap<>();

            for (String order : orders) {

                if(order.length() >= c) combination(order, new StringBuilder(), 0, 0, c);
                //단품 개수가 코스 길이 c보다 길거나 같은 경우만
            }
            if(!map.isEmpty()) { 
                int max = Collections.max(map.values()); // 제일 많이 주문된 횟수

                if(max>=2) { // 제일 많이 주문한 횟수가 2 이상이면
                    for (String key : map.keySet()) { 
                        if(map.get(key)==max) answerList.add(key); 
                    }
                }
            }

        }
        Collections.sort(answerList); 
        return answerList.toArray(new String[0]); 
    }
    private void combination(String order, StringBuilder sb, int start, int depth, int r) {
        if(depth == r) { //탈출 조건, 목표한 개수인 r만큼 알파벳을 다 뽑았으면
            String courseMenu = sb.toString(); 
            map.put(courseMenu, map.getOrDefault(courseMenu, 0)+1);
            return;
        }

        for(int i=start; i<order.length(); i++) {
            sb.append(order.charAt(i)); // 알파벳 하나를 StringBuilder에 담기
            combination(order, sb, i+1, depth+1, r); // 다음 알파벳
            sb.deleteCharAt(sb.length()-1);
        }
    }
}