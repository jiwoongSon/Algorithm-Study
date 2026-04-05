#  0404 무인도 여행
## 풀이
- 1차원 배열로 주어짐 -> 2차원 배열로 변환
    - 이때 문자 X가 포함되어 있으니, 문자열로 변환? 
    - 또는 X를 -1으로 지정하여 갈 수 없음을 표시


## 생각해볼 점
- 배열의 경우, length가 고정되어 있다. 따라서 길이를 return하는 메서드를 사용하는 것이 아니라 array interface의 상수를 이용한다. String의 경우는 length가 동적으로 변하므로, length()

```java
        for(int i = 0; i<n; i++) {
            matrix[i] = maps[i].toCharArray();
        }
```
1차원 배열을 2차원 배열로 배열로 바꾸는 과정에서, for문 중첩보다 빠르다. 
maps[i].toCharArray(); 이 부분에서 1차원 배열을 생성한다. 그 1차원 배열을 matrix[i]에 할당

```java
Queue<int[]> que = new LinkedList<>();
```
BFS는 가장 가까운 곳부터 공평하게 탐색하는 것이다. 따라서, FIFO 구조를 가져야 한다. Queue는 보통 linkedList, ArrayDeque로 구현. 

여기서의 que, 다익스트라 알고리즘의 priorityQue 등은 탐색 예정인 대기열이다. 이때 다음에 어떤 것을 탐색하는지에 따라(BFS, DFS) 다른 자료구조를 사용하면 된다. 

## 성능
O(N * M log(N * M))
2차원 배열 준비, BFS 탐색 N*M (맵의 크기)
정렬하는 경우 KlogK 
    - 내부적으로 Sort는 Timsort 사용
    - 삽입정렬과 병합정렬의 장점만 사용
    - 전체 데이터를 Run(작은 덩어리) -> Run 내부는 삽입 정렬 사용 -> Run끼리 merge sort로 정렬