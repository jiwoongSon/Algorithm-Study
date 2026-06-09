#  0609 정수 삼각형 

## 풀이
- GREEDY하게 풀게 되는 경우, 아래와 같은 경우에서 합의 최댓값을 선택할 수 없다. 따라서, 현재"까지"의 값을 메모하며 진행하는 알고리즘

```text
       7
     3   8
   99  1   0
```

## Dynamic Programming DP 
- 동적 계획법, 이름에 큰 의미는 없다.
- **Memoriziation**
    - Top-down, Bottom-up 두가지 방식이 있다.
    - 값을 누적하여 갱신? 메모? 

### 하향식
- 맨 위에서 두번째 줄부터 시작. 
-  각 칸마다, 나에게 내 부모 중 더 큰 누적값을 더함. 
```java
class Solution {
    public int solution(int[][] triangle) {

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                

                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][j];
                }

                else if (j == i) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                }

                else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }

        int answer = 0;
        int lastRow = triangle.length - 1;
        for (int i = 0; i < triangle[lastRow].length; i++) {
            answer = Math.max(answer, triangle[lastRow][i]);
        }

        return answer;
    }
}
```
### 상향식
- 맨 아래에서 두번째 줄부터 시작
- 내 아래칸 기준, 양 쪽 중에 더 큰 값을 내 칸에 더한다. 
```java
class Solution {
    public int solution(int[][] triangle) {
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                

                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        return triangle[0][0];
    }
}
```
## 개선할 점(코드)
## 성능