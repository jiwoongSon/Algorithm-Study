#  0328 배달

## 풀이
경로와 가중치가 3*N 크기의 배열로 주어진다. 
[1,2,3] -> 1번 마을과 2번 마을을 연결하는 경로가 있고, 해당 경로의 가중치는 3이다. 
이떄 2차원배열 요소의 마지막 원소가 K보다 작으면, 다른 마을을 거치지 않고 배달이 가능하다는 뜻

1번 마을에서 나머재 N-1개의 마을에 대한 경로 가중치의 최솟값을 구한다. -> 다익스트라 알고리즘 사용 (최단경로 탐색)

다익스트라의 핵심 원리는 **"지금 큐에서 꺼낸 놈이 현재로서 가장 싼 놈이라면, 그놈까지 가는 최단 거리는 절대로 더 이상 바뀔 리가 없다"**는 확신
```java
List<List<int[]>> graph = new ArrayList<>();
```
이 부분에서 끝나는게 아니라, for문으로 각 아이템의 객체를 선언하여 초기화해줘야 한다. Object가 아이템으로 들어가는 배열이나 리스트는 무조건 직접 new로 객체를 생성해줘야 한다. 



## 생각해볼 점
정보처리기사에서는, 다익스트라 알고리즘과 벨만포드 알고리즘의 내용이 RIP와 OSPF에 등증한다. 

벨만 포드 알고리즘은 다익스트라 알고리즘과 마찬가지로 최단 경로 합을 구할 수 있고, 음수도 계산이 가능하다. 벨만 포드 알고리즘은 동적 계획 DP로 경로 즉 모든 간선을 매번 확인하여 갱신하지만, 다익스트라 알고리즘은 그리디하게 탐색한다는 차이점이 있다. 


이때 RIP는 벨만포드 알고리즘 기반인데, RIP는 홉수를 기준으로 계산한다. 즉 가중치의 최소합이 아니라 최소 홉으로 최단경로를 구한다. 

OSPF는 경로상의 가중치가 가장 작은 경로를 선택한다. 

코딩테스트 관련해서, 벨만 포드 알고리즘을 요구하는 문제는 거의 없다고 한다. 

다익스트라 알고리즘 구현 -> 우선순위 큐



### PriorityQueue
default는 작은 숫자가 먼저 나오게끔, 큰 숫자가 나오게끔 하려면 Collections.reverseOrder() 메서드 사용 

- heap tree 자료구조를 사용한다. 
    - 완전이진트리, 직접 구현하려면 배열의 인덱스 연산으로 구현해야 한다. (i * 2, i * 2 + 1)
    - 삽입: offer, add - O(logN)
    - 루트 노드 값 확인: peek() - O(1)
    - 루트 노드 값 확인, 제거: poll() - O(logN)
        - logN은 밑이 2이다. N은 아이템의 개수고 해당 로그식은 트리의 높이
    - Min heap : 루트 노드가 제일 작음
    - Max heap : 루트 노드가 제일 큼

- array priorityqueue 구현 방법
```java
PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { //정렬 기준 설명서. 이렇게 정리해라!
    @Override
    public int compare(int[] o1, int[] o2) {
        return o1[1] - o2[1]; 
    }
});

pq.offer(new int[]{2, 50}); 
pq.offer(new int[]{3, 10});
```

람다식을 사용해서 아래와 같이 구현할 수 있다. 
Comparator interface에서, 채워야할 함수는 compare 하나이다. 따라서, 원본 익명클래스를 없애고, input과 output만 써준다. 

비교할 배열 o1 o2
-> 뒤의 계산 수행, 해당 결과를 return

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

pq.offer(new int[]{2, 50}); 
pq.offer(new int[]{3, 10});
pq.offer(new int[]{4, 100});

int[] bestWay = pq.poll(); 
System.out.println("가장 싼 길의 비용: " + bestWay[1]); // 10 출력
```

## 개선할 점(코드)
## 성능