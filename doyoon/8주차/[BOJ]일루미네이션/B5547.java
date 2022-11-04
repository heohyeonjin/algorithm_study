package week08.B5547;

import java.util.*;
import java.io.*;

public class B5547 {
    public static int W, H;
    public static int[][] map, visit;

    public static int[] dx = {-1, 0, 1, 1, 0, -1};
    public static int[] dyEven = {-1, -1, -1, 0, 1, 0};
    public static int[] dyOdd = {0, -1, 0, 1, 1, 1};

    public static int bfs(int x, int y) {
        int cnt = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[x][y] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX, curY;

            for (int i=0; i<6; i++) {
                curX = cur[0] + dx[i];
                if (cur[0] % 2 == 0) curY = cur[1] + dyEven[i];
                else curY = cur[1] + dyOdd[i];

                if (curX < 0 || curY < 0 || curX >= H+2 || curY >= W+2) continue;
                if (map[curX][curY] == 0 && visit[curX][curY] == 0) {
                    queue.add(new int[]{curX, curY});
                    visit[curX][curY] = 1;
                    continue;
                }
                if (map[curX][curY] == 1) cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H+2][W+2];
        visit = new int[H+2][W+2];
        for (int i=1; i<=H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=W; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = bfs(0, 0);
        System.out.println(answer);
    }
}
