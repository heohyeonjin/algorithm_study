## 문제 유형
- 우선순위 큐
- 정렬

## 코드
![img.png](img.png)
## 로직
- Lecture(start,end)배열을 시작 시각 기준으로 오름차순 정렬하고 순회한다.
- Priority Queue에 lecture[0]의 끝나는 시각을 넣는다(초기화).
- priority queue에서 나온 가장 빨리 끝나는 시각보다 lecture 배열에서의 시작시간이 크거나 같으면 해당 queue를 poll()한다.
  (같은 강의실에 배치되기 때문)
- lecture의 끝나는 시각을 큐에 넣는다.
- priority queue의 크기가 강의실의 갯수이다.
## 리뷰
이런 문제 유형들 좀 더 익숙해져야 할 것 같다.

