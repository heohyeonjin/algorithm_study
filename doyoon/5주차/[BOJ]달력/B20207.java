package week05.B20207;

import java.util.*;
import java.io.*;

// 달력
class Schedule implements Comparable<Schedule> {
    int start, end;
    Schedule(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Schedule o) {
        if (o.start == this.start) return this.end - o.end;
        return this.start - o.start;
    }
}

public class B20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Schedule> arr = new ArrayList<>();
        int minDay = Integer.MAX_VALUE, maxDay = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr.add(new Schedule(start, end));
            minDay = Math.min(minDay, start);
            maxDay = Math.max(maxDay, end);
        }

        Collections.sort(arr);
        int[] dp = new int[maxDay + 2];
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();

        for (Schedule s : arr) {
            if (pqueue.isEmpty()) pqueue.offer(s.end);
            else {
                if (pqueue.peek() < s.start) pqueue.poll();
                pqueue.offer(s.end);
            }
            for (int i=s.start; i<=s.end; i++) dp[i]++;
        }

        int answer = 0;
        int length = 0, height = 0;
        for (int i=minDay; i<=maxDay+1; i++) {
            if (dp[i] == 0) {
                answer += length * height;
                length = 0;
                height = 0;
            }
            else {
                length++;
                height = Math.max(height, dp[i]);
            }
        }

        System.out.println(answer);
    }
}
