# 0607 DFS, Backtracking
- 모든 경우의 수를 찾을 때
- 계산량이 매우 크다. 따라서 n이 작을 때 생각해봄직 하다. 
- Phone Number 
    - 문자와 숫자가 같이 써져있는 숫자 키패드에서, 숫자를 입력받았다. 이때 해당 숫자열을 만들 수 있는 모든 가능한 문자의 경우의 수? 

```java
// Phone Number
import java.util.*;

class Solution {
    String[] keypad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    // 0,1번에는 대응되는 문자열이 없음. 

    List<String> result = new ArrayList<>();

    public List<String> solution(String digits) { //digits는 입력 문자열
        dfs(digits, 0, new StringBuilder());
        return result;
    }

    public void dfs(String stringDigits, int index, StringBuilder current) {

        if(index == stringDigits.length()) { //종료조건
            result.add(current.toString());
            return;
        } 

        int intDigit = stringDigits.charAt(index) - '0';
        String letters = keypad[intDigit];
        // 숫자 2의 경우 letter에는 "abc" 저장

        for(char letter : letters.toCharArray()) {
            current.append(letter);
            dfs(stringDigits, index+1, current);

            //Backtracking!
            current.deleteCharAt(current.length()-1);
            //deleteCharAt(INDEX) 문자열에서 해당 인덱스의 원소 제거
        }
    }
}
```

- StringBuilder
    - String은 수정할 수 없다. 수정이 아니라 새로운 Object를 찍어낸다. 
    - dfs처럼 많은 연산량의 경우엔 사용이 불가
    - 스트링빌더는 가변적이다. 내부적으로 Buffer를 가지고 있어서 기존 공간 안에서 수정이 가능
        - 내부 버퍼 사이즈는 초기 문자열 길이 + 16char (2Byte)
            - 영어, 숫자의 경우에는 char 내부적으로 1글자당 1Byte로 저장한다. (Compact Strings)
        - 확장이 필요한 경우, 기존 버퍼 * 2 + 2
        - 초기 사이즈를 크게 선언하면 조금 더 빨라짐.
            - new StringBuilder(10000);
    - StringBuilder.append() // .deleteCharAt(INDEX) // .length()