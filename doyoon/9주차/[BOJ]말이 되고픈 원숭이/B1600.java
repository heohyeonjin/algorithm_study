package week09.B1600;

import java.util.*;
import java.io.*;

class Node {
    int x, y, k, count;
    Node(int x, int y, int k, int count) {
        this.x = x;
        this.y = y;
        this.k = k;
        this.count = count;
    }
}

public class B1600 {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visit;

    static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visit[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.x == H-1 && cur.y == W-1) return cur.count;

            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (!visit[nx][ny][cur.k] && map[nx][ny] == 0) {
                    queue.offer(new Node(nx, ny, cur.k, cur.count+1));
                    visit[nx][ny][cur.k] = true;
                }
            }
            if (cur.k < K) {
                for (int i=0; i<8; i++) {
                    int nx = cur.x + hx[i];
                    int ny = cur.y + hy[i];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if (!visit[nx][ny][cur.k+1] && map[nx][ny] == 0) {
                        queue.offer(new Node(nx, ny, cur.k+1, cur.count+1));
                        visit[nx][ny][cur.k+1] = true;
                    }
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());   // 가로
        H = Integer.parseInt(st.nextToken());   // 세로

        map = new int[H][W];
        visit = new boolean[H][W][K+1];
        for (int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<W; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(BFS());
    }
}
