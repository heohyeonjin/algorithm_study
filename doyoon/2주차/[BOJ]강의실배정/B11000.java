package week02.B11000;

import java.util.*;
import java.io.*;

// 강의실 배정
class Room implements Comparable<Room> {
    int start, end;

    Room(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Room o) {
        if (this.start == o.start) return this.end - o.end;
        return this.start - o.start;
    }
}

public class B11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Room> arr = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr.add(new Room(start, end));
        }

        Collections.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr.get(0).end);

        for (int i=1; i<arr.size(); i++) {
            if (arr.get(i).start >= pq.peek()) pq.poll();
            pq.offer(arr.get(i).end);
        }

        System.out.println(pq.size());

//        ArrayList<Integer> answer = new ArrayList<>();
//        for (Room o : arr) {
//            if (answer.isEmpty()) answer.add(o.end);
//            else {
//                boolean flag = false;
//                Collections.sort(answer);
//                for (Integer x : answer) {
//                    if (o.start >= x) {
//                        answer.remove(x);
//                        answer.add(o.end);
//                        flag = true;
//                        break;
//                    }
//                }
//                if (!flag) answer.add(o.end);
//            }
//        }
//
//        System.out.println(answer.size());
    }
}
