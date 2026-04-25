# 0425 소수 찾기

## 풀이
처음에 나온 number를 쪼개서 중복 제거하면 안됨. 숫자 하나 == 경우의 수를 만들 수 있는 하나의 카드. 
굳이 array로 바꿀 필요 없이, charAt(index) 사용하고 마지막에 Integer 형변환 해주는게 나을듯 

n개의 숫자들을 조합하여 만들 수 있는 모든 숫자 만들기 
    - n자리인 경우, 10^n *  + 10^(n-1) *  ... 
    - 근데 이렇게하면 n자리만 찾을 수 있다. -> 재귀함수 

위의 방법으로 모든 가능한 수를 찾고, 소수를 찾는 함수 isPrime 적용
이때 반드시 0과 1은 소수가 아니라는것을 명시


## 생각해볼 점
- 문자열 -> 숫자 변환 : Interger Class의 메서드 사용
    - Integer.valueOf()
    - Integer.parseInt()
        -  정수 객체를 리턴, 즉 new Integer(Integer.ParseInt(s))와 동일


- (String).split("") : return값은 String이 아닌 array이다. 


### isPirme 저 방법이 최선인가? 

- 소수찾기 알고리즘. 숫자를 받아서 1-n까지 다 나눠보는게 최선인가?
    - 제곱근 판별, 위의 방법이랑 로직은 똑같다. 근데 절반만 검사해봐도 된다는 장점이 있다. **기하평균을 이용.**
```java
    public boolean isPrime(int number) {

    if (number < 2) {
        return false;
    }
    
    for (int i = 2; i <= (int) Math.sqrt(number); i++) {
        if (number % i == 0) {
            return false;
        }
    }
    return true;
}
```

- 에라토스테네스의 체
대량의 범위에서 사용
1. 2~N까지 나열
2. 2를 소수로 채택, 2의 배수 다 지우기
3. 3를 소수로 채택, 3의 배수 다 지우기 
4. 반복

```java
import java.util.Arrays;

public void sieveOfEratosthenes(int maxNumber) {

    boolean[] isPrime = new boolean[maxNumber + 1];
    
    Arrays.fill(isPrime, true); // 일단 다 소수라고 가정
    
    isPrime[0] = false;
    isPrime[1] = false;
    

    for (int i = 2; i <= (int) Math.sqrt(maxNumber); i++) { // 마찬가지로 기하평균 이용 
        
        if (isPrime[i]) {
            for (int j = i * i; j <= maxNumber; j += i) { // i*2부터 시작해도 되는데, i보다 작은 수들은 이미 지웠으니까 굳이 다시 초기화할 필요가 없다. 
                isPrime[j] = false;
            }
        }
    }
    
    for (int i = 2; i <= maxNumber; i++) {
        if (isPrime[i]) {
            System.out.print(i + " ");
        }
    }
}
```

## 개선할 점(코드)
isPirme 개선

## 성능
경우의 수 생성 O(N!)
각 경우의 수마다, 소수 판별 -> O(N!*10^N)

