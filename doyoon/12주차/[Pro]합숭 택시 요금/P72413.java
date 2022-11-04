package week12.P72413;

import java.util.*;

// 합승 택시 요금
public class P72413 {
    public ArrayList<ArrayList<int[]>> arr;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        arr = new ArrayList<>();
        for (int i=0; i<=n; i++) arr.add(new ArrayList<>());

        for (int[] fee : fares) {
            arr.get(fee[0]).add(new int[] {fee[1], fee[2]});
            arr.get(fee[1]).add(new int[] {fee[0], fee[2]});
        }

        int[] startS = new int[n+1];
        int[] startA = new int[n+1];
        int[] startB = new int[n+1];
        Arrays.fill(startS, Integer.MAX_VALUE);
        Arrays.fill(startA, Integer.MAX_VALUE);
        Arrays.fill(startB, Integer.MAX_VALUE);

        startS = dijkstra(s, startS);
        startA = dijkstra(a, startA);
        startB = dijkstra(b, startB);

        for (int i=1; i<=n; i++) answer = Math.min(answer, startS[i] + startA[i] + startB[i]);

        return answer;
    }

    public int[] dijkstra(int start, int[] dist) {
        PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
        pqueue.offer(new int[]{start, 0});
        dist[start] = 0;

        while (!pqueue.isEmpty()) {
            int[] cur = pqueue.poll();

            if (dist[cur[0]] < cur[1]) continue;

            for (int[] node : arr.get(cur[0])) {
                if (dist[cur[0]] + node[1] < dist[node[0]]) {
                    dist[node[0]] = dist[cur[0]] + node[1];
                    pqueue.offer(node);
                }
            }
        }
        return dist;
    }
}
