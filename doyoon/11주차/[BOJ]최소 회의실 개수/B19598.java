package week11.B19598;

import java.util.*;
import java.io.*;

// 최소 회의실 개수
public class B19598 {
    public static int solution(int N, int[][] time) {
        Arrays.sort(time, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pqueue = new PriorityQueue<>();
        pqueue.offer(time[0][1]);

        for (int i=1; i<N; i++) {
            int[] meet = time[i];
            if (pqueue.peek() <= meet[0]) pqueue.poll();
            pqueue.offer(meet[1]);
        }

        return pqueue.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] time = new int[N][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, time));
    }
}
