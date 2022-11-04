package week07.B22856;

import java.util.*;
import java.io.*;

// 트리 순회
public class B22856 {
    public static int[][] tree;
    public static int N, visit = 0, answer = 0;

    public static void traversal(int cur) {
        if (tree[cur][0] != -1) {
            answer++;
            traversal(tree[cur][0]);
        }
        visit++;
        if (tree[cur][1] != -1) {
            answer++;
            traversal(tree[cur][1]);
        }
        if (visit != N) answer++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        tree = new int[N+1][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            tree[cur][0] = Integer.parseInt(st.nextToken());
            tree[cur][1] = Integer.parseInt(st.nextToken());
        }

        traversal(1);
        System.out.println(answer);

    }
}