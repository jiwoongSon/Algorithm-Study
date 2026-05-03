#  0503 피로도

## 풀이
k는 현재 가지고 있는 피로도(energy 라고 생각) -> postive 개념

{ {최소필요피로도1, 소모피로도1}, 
 {최소필요피로도2, 소모피로도2} ... }

던전의 수를 최대한 많이 탐험

완전탐색 -> bfs or dfs ? 
탐험한 던전의 수를 기록할 변수 int answer
### 풀이 1 
```java
import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {

        int answer = -1;

        boolean isVisited[] = new boolean[dungeons.length];
        for(int firstIndex=0; firstIndex<dungeons.length; firstIndex++) {
            dfs(k, dungeons, firstIndex, answer, isVisited);
        }

        return answer;
    }

    public void dfs(int k, int[][] dungeons, int index, int answer, boolean[] isVisited) {        
        
        isVisited[index] = true;
        answer++;
        k -= dungeons[index][1];

        for(int i = 0; i<dungeons.length; i++) {
            if(!isVisited[index] && k < dungeons[index][0]) {
                dfs(k, dungeons, i, answer, isVisited);
            }
        }
    }
}
```
문제점 1. if문 조건검사 반대로함. 피로도가 더 클 때 던전에 출입해야 한다. 

문제점 2. 클래스 전역변수로 answer를 선언해야 한다. call by value.. 
**근데 이런 코딩테스트에서는, 클래스 변수로 함부로 선언하면 안됨. 객체를 하나만 만들어서 테스트 할 수 도 있기 때문 -> 다음으로 testcase로 값이 넘어간다.**
-> 해결 1. 클래스에서 선언만 하고, Solution 함수에서 초기화 
-> 해결 2. call by reference : 즉 길이가 1인 배열로 선언해서 넘겨줄 수 도 있다. 

문제점 3. firstIndex는 조건 검사를 안함. 조건 검사를 붙여도, Solution 함수에서 이중으로 진행해야 한다. 

문제점 4. backtracking의 부재. 한번 true로 초기화된 isVisit 배열은 다시는 false로 초기화 되지 않음. 

### 풀이 2
```java
import java.util.*;

class Solution {
    int answer;

    public int solution(int k, int[][] dungeons) {
        answer = -1;

        boolean isVisited[] = new boolean[dungeons.length];
        for(int firstIndex=0; firstIndex<dungeons.length; firstIndex++) {
            if(dungeons[firstIndex][0] < k) dfs(k, dungeons, firstIndex, answer, isVisited);
        }

        return answer;
    }

    public void dfs(int k, int[][] dungeons, int index, int answer, boolean[] isVisited) {        
        
        isVisited[index] = true;
        answer++;
        k -= dungeons[index][1];

        for(int i = 0; i<dungeons.length; i++) {
            if(!isVisited[index] && k >= dungeons[index][0]) {
                dfs(k, dungeons, i, answer, isVisited);
                isVisited[index] = false;
            }
        }
    }
}
```
문제점 1. answer의 Shadowing 현상. 아예 매개변수에서 지워야한다. 
문제점 2. index랑 i 오타
**문제점 3. 백트레킹 위치** dfs 탐색을 탈출 할 때 false로 초기화 해야한다.
문제점 4. 각 시작 index또한 answer를 공유해서 최대한값이 나오는게 아니게 된다. 

### 최종 셀프풀이
```java
import java.util.*;

class Solution {
    int answer; 

    public int solution(int k, int[][] dungeons) {
        answer = 0; 

        boolean isVisited[] = new boolean[dungeons.length];
        
        for(int firstIndex = 0; firstIndex < dungeons.length; firstIndex++) {
            if(dungeons[firstIndex][0] <= k) {
                dfs(k, dungeons, firstIndex, 1, isVisited);
            }
        }

        return answer;
    }

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
```

## 생각해볼 점
length 메서드의 괄호 유무
    - array 등 크기가 고정된 배열에서는 length가 멤버 변수이다. 즉 읽어오기만 하면 되므로 괄호가 필요 없음
    - String 객체나, Collection 등 가변길이는 length() 즉 함수로 측정해야함. 

처음으로 어느정도 해결해봤다. bfs 함수의 핵심 로직인 내부 for문을 대충 비슷하게 짰다는거에서 만족

## 개선할 점(코드)
사실 효율적인 코드는 아니다. 
```java
class Solution {
    int maxAnswer;

    public int solution(int k, int[][] dungeons) {
        maxAnswer = 0; 
        boolean[] isVisited = new boolean[dungeons.length];
        dfs(k, dungeons, 0, isVisited);
        return maxAnswer;
    }

    public void dfs(int k, int[][] dungeons, int count, boolean[] isVisited) {
        maxAnswer = Math.max(maxAnswer, count);

        for (int i = 0; i < dungeons.length; i++) {
            if (!isVisited[i] && k >= dungeons[i][0]) {
                
                isVisited[i] = true; 
                dfs(k - dungeons[i][1], dungeons, count + 1, isVisited);
                isVisited[i] = false; 
                
            }
        }
    }
}
```

억지로, 모든 시작점을 확인하려고 for문으로 돌렸는데 그럴 필요 없이 backtracking을 이해하고 있으면 모든 시작점에서 탐색한다는 것을 이해할 수 있었다. 
## 성능
O(N!)

## 백트레킹 관련
🔍 질문 1. 백트래킹 위치 비교 (내 코드 vs 정답 코드)
결론부터 말씀드리면, 두 방식 모두 백트래킹의 정답입니다. 단지 **'어느 시점에서 도장을 찍고 지우느냐'**하는 관점의 차이일 뿐입니다.
🚪 1. 질문자님의 마지막 코드: "방(Node) 중심" 관점
```java
public void dfs(..., int index, ...) {        
    isVisited[index] = true; // 1. 나 지금 index 방에 들어왔어! 도장 쾅!
    
    // ... (현재 방에서 할 일 하기) ...

    for(int i = 0; i < dungeons.length; i++) {
        if(!isVisited[i] && 조건) {
            dfs(..., i, ...); // 2. 다음 i번 방으로 넘어가자!
        }
    }
    
    isVisited[index] = false; // 3. 자, 이제 index 방에서 완전히 나갈게. 도장 지운다!
}
```

•	특징: 함수가 시작될 때 **'현재 내가 있는 방(index)'**을 기준으로 도장을 찍고, 함수가 끝나기 직전(방을 나갈 때) 도장을 지웁니다.
•	장단점: 내가 현재 어떤 방에 있는지 직관적으로 알기 좋습니다. 하지만 시작할 때 무조건 index가 필요하기 때문에, 처음에 solution 함수 안에서 for문을 돌려 첫 번째 방을 강제로 지정해 줘야 하는 번거로움이 있습니다.
🔑 2. 순수 정답 코드: "문(Edge/경로) 중심" 관점
```java
public void dfs(...) { // 현재 방이 어딘지는 관심 없음!
    // ...
    for (int i = 0; i < dungeons.length; i++) {
        if (!isVisited[i] && 조건) {
            
            isVisited[i] = true; // 1. i번 방 문 열고 들어간다! 도장 쾅!
            
            dfs(...);            // 2. i번 방 안으로 진입 (재귀)
            
            isVisited[i] = false; // 3. i번 방 탐색 다 끝내고 나왔어. 도장 지운다!
            
        }
    }
}
```

•	특징: 내가 지금 어디 있는지는 중요하지 않습니다. 오직 **'앞으로 들어갈 방(i)'**을 기준으로, 들어가기 직전에 도장을 찍고, 재귀를 빠져나오자마자(나왔으니까) 도장을 지웁니다.
•	장단점: 현재 위치를 뜻하는 index 파라미터가 필요 없어서 코드가 훨씬 간결해집니다. solution에서 첫 방을 지정할 필요 없이, 그냥 "아무것도 안 한 0의 상태"로 던져주면 알아서 for문이 0번 방부터 문을 열고 들어가게 됩니다.
요약: 질문자님의 코드는 "나 들어왔다 -> 나간다" 였다면, 정답 코드는 "너한테 들어갈게 -> 너한테서 나왔다" 입니다. 실전에서는 파라미터를 하나라도 줄이기 위해 후자(정답 코드)의 방식을 훨씬 많이 사용합니다!