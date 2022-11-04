package week09.B2636;

import java.util.*;
import java.io.*;

public class B2636 {
    static int[][] arr;
    static int row, col;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static boolean checkFinish() {
        for (int i=0; i<col; i++) {
            for (int j=0; j<row; j++) {
                if (arr[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static int BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        boolean[][] visit = new boolean[col][row];
        visit[x][y] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= col || ny >= row || visit[nx][ny]) continue;

                if (arr[nx][ny] == 0) queue.add(new int[]{nx, ny});
                else {
                    arr[nx][ny] = 0;
                    cnt++;
                }
                visit[nx][ny] = true;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        arr = new int[col][row];
        for (int i=0; i<col; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<row; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0, day = 0;
        while (!checkFinish()) {
            cnt = BFS(0, 0);
            day++;
        }

        System.out.println(day);
        System.out.println(cnt);
    }
}