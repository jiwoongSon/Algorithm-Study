package Baekjoon;

import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        String b = st.nextToken();
        char c = st.nextToken().charAt(0); // String의 0번째 인덱스를 char로 변환
        double d = Double.parseDouble(st.nextToken());
        // n*m 형태로 띄어쓰기 없이 주어진 경우, 한 줄을 문자열로 읽은 후 charAt()으로 하나씩 char로 변환 후 -'0' 연산으로 Int array로 변홙
        
        System.out.println("정답");
    }
}