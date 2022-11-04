package week08.B14502;

import java.util.*;
import java.io.*;

// 연구소
public class B14502 {
    public static int[][] map;
    public static int N, M, answer = Integer.MIN_VALUE;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void DFS(int cnt){
        if (cnt == 3) BFS();
        else {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = 1;
                        DFS(cnt+1);
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void BFS() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 2) queue.add(new int[] {i, j});
            }
        }

        int[][] newMap = new int[N][M];
        for (int i=0; i<N; i++) newMap[i] = map[i].clone();

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (newMap[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    newMap[nx][ny] = 2;
                }
            }
        }

        int temp = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (newMap[i][j] == 0) temp++;
            }
        }
        answer = Math.max(answer, temp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        DFS(0);
        System.out.println(answer);
    }
}
