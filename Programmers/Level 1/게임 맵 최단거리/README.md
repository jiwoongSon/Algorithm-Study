#  0331 게임 맵 최단거리

## 풀이
BFS, DFS 
비용이 똑같은 다익스트라 알고리즘 


## 생각해볼 점
### Depth-First Search 
갈림길 -> 한쪽으로만 진행
stack, recursion 이용

### Breadth-First Search
현 위치에서 가까운 곳부터 퍼저나감
Queue 이용 (FIFO)

r, c? : row, column
수학 좌표랑 좀 다르다. 배열의 축은 반대로 되어있어서 헷갈리지 않으려면 r,c 쓰는게 나을듯

```java
Queue<int[]> queue = new LinkedList<>(); 
```
자바에서 큐는 링크드리스트로 구현한다. 


## 개선할 점(코드)
## 성능
O(n*m)