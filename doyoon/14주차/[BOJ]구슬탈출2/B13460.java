package week14.B13460;

import java.util.*;
import java.io.*;

// 구슬 탈출 2
public class B13460 {
    static char[][] map;
    static Queue<int[]> queue = new LinkedList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int BFS() {
        int answer = 1;
        while (!queue.isEmpty()) {
            if (answer > 10) return -1;

            int len = queue.size();
            for (int i=0; i<len; i++) {
                int[] cur = queue.poll();

                for (int k=0; k<4; k++) {
                    boolean isRedHole = false, isBlueHole = false;

                    int nxR = cur[0];
                    int nyR = cur[1];
                    while (true) {
                        if (map[nxR][nyR] == 'O') isRedHole = true;
                        if (map[nxR + dx[k]][nyR + dy[k]] == '#') break;
                        nxR += dx[k];
                        nyR += dy[k];
                    }

                    int nxB = cur[2];
                    int nyB = cur[3];
                    while (true) {
                        if (map[nxB][nyB] == 'O') isBlueHole = true;
                        if (map[nxB + dx[k]][nyB + dy[k]] == '#') break;
                        nxB += dx[k];
                        nyB += dy[k];
                    }

                    if (isBlueHole) continue;
                    if (isRedHole) return answer;

                    if (nxR == nxB && nyR == nyB) {
                        if (k == 0) {           // 상
                            if (cur[0] < cur[2]) nxB -= dx[k];
                            else nxR -= dx[k];
                        }
                        else if (k == 1) {      // 좌
                            if (cur[1] < cur[3]) nyB -= dy[k];
                            else nyR -= dy[k];
                        }
                        else if (k == 2) {      // 하
                            if (cur[0] > cur[2]) nxB -= dx[k];
                            else nxR -= dx[k];
                        }
                        else {                  // 우
                            if (cur[1] > cur[3]) nyB -= dy[k];
                            else nyR -= dy[k];
                        }
                    }

                    queue.offer(new int[] {nxR, nyR, nxB, nyB});
                }
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i=0; i<N; i++) {
            String str = br.readLine();
            for (int j=0; j<M; j++) map[i][j] = str.charAt(j);
        }

        int[] bead = new int[4];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 'R') {
                    bead[0] = i;
                    bead[1] = j;
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    bead[2] = i;
                    bead[3] = j;
                    map[i][j] = '.';
                }
            }
        }
        queue.offer(bead);
        System.out.println(BFS());
    }
}