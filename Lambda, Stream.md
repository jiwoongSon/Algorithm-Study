# Lambda

```java
int add(int a, int b) {
    return a + b;
}

---

(a,b) -> a+b // a,b가 들어오면 a+b를 리턴해라
```

```java

// 원래는 알파벳 순서로 정렬, 하지만 짧은 순으로 정렬하고싶음 
String[] words = {"apple", "banana", "kiwi"};

Arrays.sort(words, new Comparator<String>() {
    @Override //Comparator 인터페이스의 compare함수
    public int compare(String s1, String s2) {
        return s1.length() - s2.length(); // 길이 순으로 정렬
    }
});

---

Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());

```

```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 0);

// 람다의 forEach: "맵에 있는 key, value 쌍에 대해서 괄호 안의 동작을 수행해라"
map.forEach((key, value) -> {
    if (value != 0) {
        System.out.println("미완주자: " + key);
    }
});
```
---
# Stream
what? 무엇을 할 지
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> result = new ArrayList<>();

for (int n : numbers) {
    if (n % 2 == 0) {       // 1. 짝수인가?
        result.add(n * 2);  // 2. 2배 곱해서 넣어라
    }
}
```

```java
List<Integer> result = numbers.stream()               // 1. 생성: 벨트에 올린다
    .filter(n -> n % 2 == 0)                          // 2. 가공: 짝수만 걸러낸다
    .map(n -> n * 2)                                  // 3. 가공: 2배로 형태를 바꾼다
    .collect(Collectors.toList());                    // 4. 결과: 리스트로 포장해라
```

```java
int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};

int[] result = Arrays.stream(arr) // 생성
    .distinct()                   // 가공: 중복 제거 (알아서 Set처럼 동작)
    .boxed()                      // 가공: int를 Integer 객체로 포장 (정렬을 위해)
    .sorted(Collections.reverseOrder()) // 가공: 내림차순 정렬
    .mapToInt(Integer::intValue)  // 가공: 다시 int 기본형으로 변환
    .toArray();                   // 최종: int[] 배열로 뽑아내기
```