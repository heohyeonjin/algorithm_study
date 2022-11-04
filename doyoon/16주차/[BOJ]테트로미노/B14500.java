package week16.B14500;

import java.io.*;
import java.util.*;

// 테트로미노
public class B14500 {
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visit;

    static int answer = Integer.MIN_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void DFS(int x, int y, int cnt, int sum) {
        if (answer >= sum + max * (4-cnt)) return;
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (!visit[nx][ny]) {
                visit[nx][ny] = true;
                DFS(nx, ny, cnt + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }

    public static void remainder(int x, int y) {
        int sum = map[x][y];
        PriorityQueue<Integer> pqueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            pqueue.offer(map[nx][ny]);
        }

        if (pqueue.size() < 3) return;
        for (int i=0; i<3; i++) sum += pqueue.poll();
        answer = Math.max(answer, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        visit = new boolean[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                visit[i][j] = true;
                DFS(i, j, 1, map[i][j]);
                visit[i][j] = false;
                remainder(i, j);
            }
        }

        System.out.println(answer);
    }
}