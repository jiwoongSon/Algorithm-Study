# 평행 0325
## 풀이
1. 
```java
import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        List<Integer> inclineList = new ArrayList<>();
        for(int i = 0; i<4; i++) {
            for(int j = 3; j<4-i; j--) {
                int incline = (dots[i][1] - dots[i+j][1]) / (dots[i][0] - dots[i+j][0]);
                inclineList.add(incline);
            }
        }
        Set<Integer> inclineSet = new HashSet<>(inclineList);
        if(inclineSet.size()!=inclineList.size()) return 1;
        else return 0;
    }
}

// 4*3/2 = 6개 경우의 수
// if조건으로 6개 짜도 됨. 조금 더 나은 방법? 
// 순열의 경우, 이중 for문으로 해결, 근데 조합의 모든 경우의 수를 나타내야 한다면? 
// 기울기 이용 yn-ym/xn-xm

//00 01
//10 11
//20 21
//30 31

// 일단 모든 선의 기울기를 구해야됨. 
```
이렇게 하면, 두번쨰 중첩된 For문에서 j가 계속 감소한다. OutOfBoundException 발생


2. 
```java
import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        List<Double> inclineList = new ArrayList<>();
        for(int i = 0; i<3; i++) { //3인 이유는, 첫번째 비교대상이 4번째 dot일 필요가 없기 때문. OutOfIndexException
            for(int j = i+1; j<4; j++) {
                double incline = (double)(dots[i][1] - dots[j][1]) / (dots[i][0] - dots[j][0]);
                inclineList.add(incline);
            }
        }
        Set<Double> inclineSet = new HashSet<>(inclineList);
        if(inclineSet.size()!=inclineList.size()) return 1;
        else return 0;
    }
}
```
고친 부분이 많다.

- 모든 조합의 경우의 수를 구할 떄의 로직은 위와 같다. index에 --연산을 하면 생각할 부분이 많아진다. 
    - [1,2,3,4,5] 가 있다고 하면 12 13 14 15, 23 24 25, 34 35, 45
    - 내 경우에는 15 14 13 12, "11" -> 여기서부터 문제가 발생한다. 
- 나눗셈 부분의 형변환을 체크하지 못했다. int 나눗셈의 경우, truncate되므로 항상 명시적 형변환을 체크해주자. 

3. 문제를 잘 읽자. 해당 문제는 주어진 네 개의 점을 두 개씩 이었을때, 즉 1234라는 점이 있으면 case는 
12 34
13 24
14 23
딱 이렇게 세쌍밖에 나오지 않는다.. 따라서 아래의 코드처럼 하드코딩하는것이 유일하고 가장 깔끔한 풀이다. 
```java
class Solution {
    public int solution(int[][] dots) {
        // 1. [0,1] 과 [2,3] 
        double slope1 = (double)(dots[0][1] - dots[1][1]) / (dots[0][0] - dots[1][0]);
        double slope2 = (double)(dots[2][1] - dots[3][1]) / (dots[2][0] - dots[3][0]);
        if (slope1 == slope2) return 1;

        // 2. [0,2] 와 [1,3]
        double slope3 = (double)(dots[0][1] - dots[2][1]) / (dots[0][0] - dots[2][0]);
        double slope4 = (double)(dots[1][1] - dots[3][1]) / (dots[1][0] - dots[3][0]);
        if (slope3 == slope4) return 1;

        // 3. [0,3] 과 [1,2]
        double slope5 = (double)(dots[0][1] - dots[3][1]) / (dots[0][0] - dots[3][0]);
        double slope6 = (double)(dots[1][1] - dots[2][1]) / (dots[1][0] - dots[2][0]);
        if (slope5 == slope6) return 1;

        return 0;
    }
}
```

## 생각해볼 점

- <> : 제네릭, 담을 물건의 종류 지정. 자바의 컬렉션에는 무조건 객체만 들어갈 수 있다. 따라서 기본 type의 data을 넣으려면 객체형태로 감싸주는 다이아몬드 연산자가 들어가야 한다. 
- () : ArrayList 생성자를 호출

조합의 모든 경우의 수를 구하는 로직
```java
for (int i = 0; i < dots.length - 1; i++) {
    for (int j = i + 1; j < dots.length; j++) {
        // dots[i]와 dots[j] 두 점을 선택
    }
}
```

## 개선할 점(코드)
```java
class Solution {
    public int solution(int[][] dots) {
        // 경우의 수가 3개뿐이므로 하드코딩으로 직관적으로 비교하는 것이 더 깔끔합니다.
        
        // 1. [0-1] 과 [2-3] 비교
        if (isParallel(dots[0], dots[1], dots[2], dots[3])) return 1;
        
        // 2. [0-2] 와 [1-3] 비교
        if (isParallel(dots[0], dots[2], dots[1], dots[3])) return 1;
        
        // 3. [0-3] 과 [1-2] 비교
        if (isParallel(dots[0], dots[3], dots[1], dots[2])) return 1;
        
        return 0; // 평행한 경우가 없으면 0 반환
    }
    
    // 두 직선이 평행한지 확인하는 메서드 (나눗셈 대신 곱셈 사용으로 소수점 및 0나누기 방지)
    private boolean isParallel(int[] dot1, int[] dot2, int[] dot3, int[] dot4) {
        int xDiff1 = dot1[0] - dot2[0];
        int yDiff1 = dot1[1] - dot2[1];
        
        int xDiff2 = dot3[0] - dot4[0];
        int yDiff2 = dot3[1] - dot4[1];
        
        // (y1-y2) * (x3-x4) == (y3-y4) * (x1-x2)
        return yDiff1 * xDiff2 == yDiff2 * xDiff1;
    }
}
```

경우의 수가 적으므로, if문으로 직접 case를 써보는것도 좋을 듯 하다.

## 성능
