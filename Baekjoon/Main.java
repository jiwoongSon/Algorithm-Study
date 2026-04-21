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
        
        System.out.println("정답");
    }
}