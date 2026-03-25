# 옹알이(1) 0324
## 풀이
1. equals() : 문자열이 같은지 확인한다. 해당 경우에는, aaaya를 발음하지 못한다고 인식한다. 문제를 제대로 읽지 않았다. 
```java
import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        int length = babbling.length;
        
        for(int i = 0; i<length; i++) {
            if(babbling[i].equals("aya")) {
                answer++;
                continue;
            }  
            if(babbling[i].equals("ye")) {
                answer++;
                continue;
            }
            if(babbling[i].equals("woo")) {
                answer++;
                continue;
            }
            if(babbling[i].equals("ma")) {
                answer++;
                continue;
                }
    }
    return answer;

}
}
```

2. indexOf 사용? 문자열에 특정 문자열이 시작하는 index를 반환한다. 이때 문자열이 존재하지 않는다면 -1 반환. 마찬가지로 문제에 대한 이해를 잘 못한 부분이다. 해당 문제는 주어진 문자열이 **발음이 가능한지** 묻는 문제이다. 예를 들어 ayaeeeee 의 경우, 2번 코드에서는 count를 하나 올리지만, 발음이 불가능한 단어이다. 

```java
import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        int length = babbling.length;
        
        for(int i = 0; i < length; i++) {
            if(babbling[i].indexOf("aya") != -1) {
                answer++;
                continue;
            }   
            if(babbling[i].indexOf("ye") != -1) {
                answer++;
                continue;
            }
            if(babbling[i].indexOf("woo") != -1) {
                answer++;
                continue;
            }
            if(babbling[i].indexOf("ma") != -1) {
                answer++;
                continue;
            }
        }
        return answer;
    }
}
```
3. 위의 if문 방식대로라면, 4개의 단어에 대한 모든 검사를 진행하여야 한다. if문 중첩으로는 해결되지 않는다. 순서를 고려하지 않아야 하는데 if문 중첩을 하는 순간 의존성이 생기기 때문 

```java
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (int i = 0; i < babbling.length; i++) {
            String word = babbling[i];

            word = word.replace("aya", " ");
            word = word.replace("ye", " ");
            word = word.replace("woo", " ");
            word = word.replace("ma", " ");
            
            if (word.trim().length() == 0) {
                answer++;
            }
        }
        
        return answer;
    }
}
```
4가지 단어를 공백으로 치환하고, 마지막으로 공백을 제거하여 알파벳이 남아있다면 해당 단어는 발음할 수 없는 단어이다. 해당 문자열의 길이가 0인 경우에만 cnt++


## 생각해볼 점
- java는 c와 달리 엄격한 타입을 가짐. 
    - C는 0은 false, 이외의 자연수는 true로 인식
    - java는 컴파일에러

- String함수 : equals(), indexOf(), replace(,)

- **향상된 For문**

## 개선할 점(코드)
```java
import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] can = {"aya", "ye", "woo", "ma"};
        
        for (String s : babbling) {
            for (String c : can) {
                s = s.replace(c, " ");
            }
            
            if (s.trim().length() == 0) {
                answer++;
            }
        }
        return answer;
    }
}
```
향상된 for문을 사용하여 코드를 조금 더 간결하게 작성가능

## 성능
for문 중첩 -> O(n^2)

## 느낀점
어제 풀었던 level3 문제보다 어렵게 느껴졌다. 알고리즘 자체의 난이도보다, 코딩 자체에 익숙하지 않아 내장함수나 loop가 더 어렵게 느껴진다. 아자스