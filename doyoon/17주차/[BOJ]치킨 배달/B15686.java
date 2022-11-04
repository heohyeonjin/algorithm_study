package week17.B15686;

import java.io.*;
import java.util.*;

// 치킨 배달
public class B15686 {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;

    static boolean[] visit;
    static ArrayList<int[]> house, chicken;

    static void DFS(int cnt, int depth) {
        if (cnt == M) answer = Math.min(getChickenDistance(), answer);
        else {
            for (int i=depth; i< chicken.size(); i++) {
                visit[i] = true;
                DFS(cnt+1, i+1);
                visit[i] = false;
            }
        }
    }

    static int getChickenDistance() {
        int distance = 0;

        for (int[] h : house) {
            int curDistance = Integer.MAX_VALUE;
            for (int i=0; i<chicken.size(); i++) {
                if (visit[i]) {
                    int[] c = chicken.get(i);
                    int temp = Math.abs(c[0] - h[0]) + Math.abs(c[1] - h[1]);
                    curDistance = Math.min(temp, curDistance);
                }
            }
            distance += curDistance;
        }

        return distance;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) house.add(new int[]{i, j});
                if (map[i][j] == 2) chicken.add(new int[]{i, j});
            }
        }

        visit = new boolean[chicken.size()];
        DFS(0, 0);
        System.out.println(answer);
    }
}
