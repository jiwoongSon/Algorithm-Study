#  0422 메뉴 리뉴얼
## 풀이
1. HashMap 이용하여, order 각 단품 메뉴에 대한 count 기록 
2. 상위 n(2이상) 개 메뉴 Count ->  HashMap 어떤 내장함수 사용? : Combination으로 조합 가능한 모든 경우의 수 
3. course의 인덱스 length-1부터(오름차순이라고 했으니까) 가장 많은 n개 count
4. 반복 
5. 이때 count가 0이거나 1이면 return하지 않음. 

## 생각해볼 점
다른 문제를 풀면서 해당 기법 숙달 필요

```java
        Collections.sort(answerList); 
        return answerList.toArray(new String[0]); 
```
answerList.toArray : 정답 리스트를 배열로 변환. -> Object타입의 array , Object[] 배열 return
문제의 리턴타입이 스트링 배열이므로, 타입에러 발생.. 괄호 안에 어떤 타입으로 바꿔주는지 작성해야 한다. 

이때 자바의 toArray(T[] a) 메서드는 괄호 안에 들어온 배열의 size를 보고
괄호 안에 주어진 배열 size < 리스트의 size : 알아서 리스트에 크크에 맞는 배열로 초기화
괄호 안에 주어진 배열 size >= 리스트의 size : 해당 공간에 할당 

new String[answerList.size()] 로 하는게 더 정확하고 빠르지 않을까? 
가능은 하지만, 최신 JVM에서는 그냥 [0] 이라고 쓰는게 더 빠르다. 국룰이라고 생각 ㅇㅇ


## 개선할 점(코드)
## 성능