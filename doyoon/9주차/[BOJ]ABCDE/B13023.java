package week09.B13023;

import java.util.*;
import java.io.*;

// ABCDE
public class B13023 {
    static boolean check;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> arr;

    public static void DFS(int cur, int sum) {
        if (sum == 5) {
            check = true;
            return;
        }

        for (int next : arr.get(cur)) {
            if (!visit[next]) {
                visit[next] = true;
                DFS(next, sum + 1);
                visit[next] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        for (int i=0; i<N; i++) arr.add(new ArrayList<>());

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        for (int i=0; i<N; i++) {
            visit = new boolean[N];
            visit[i] = true;
            DFS(i, 1);
            if (check) break;
        }

        if (check) System.out.println(1);
        else System.out.println(0);

    }
}
