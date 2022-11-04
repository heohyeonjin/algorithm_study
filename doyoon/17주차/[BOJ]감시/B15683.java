package week17.B15683;

import java.io.*;
import java.util.*;

// 감시
class CCTV {
    int[] pos;
    int type, caseCnt;

    CCTV(int[] pos, int type) {
        this.pos = pos;
        this.type = type;
        if (type == 1 || type == 3 || type == 4) this.caseCnt = 4;
        else if (type == 2) this.caseCnt = 2;
        else this.caseCnt = 1;
    }
}

public class B15683 {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[] directions;
    static ArrayList<CCTV> cctvs;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void DFS(int cur) {
        if (cur == cctvs.size()) checkSpot();
        else{
            for (int i=0; i<cctvs.get(cur).caseCnt; i++) {
                directions[cur] = i;
                DFS(cur+1);
            }
        }
    }

    public static void checkSpot() {
        int[][] newMap = new int[N][M];
        for (int i=0; i<N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, M);
        }

        for (int i=0; i<cctvs.size(); i++) {
            CCTV cctv = cctvs.get(i);
            int x = cctv.pos[0];
            int y = cctv.pos[1];

            if (cctv.type == 1) {
                while (x >= 0 && y >= 0 && x < N && y < M && newMap[x][y] != 6) {
                    if (newMap[x][y] == 0) newMap[x][y] = -1;
                    x += dx[directions[i]];
                    y += dy[directions[i]];
                }
            }
            else if (cctv.type == 2) {
                for (int k=0; k<2; k++) {
                    x = cctv.pos[0];
                    y = cctv.pos[1];
                    while (x >= 0 && y >= 0 && x < N && y < M && newMap[x][y] != 6) {
                        if (newMap[x][y] == 0) newMap[x][y] = -1;
                        x += dx[(directions[i] + 2*k) % 4];
                        y += dy[(directions[i] + 2*k) % 4];
                    }
                }
            }
            else if (cctv.type == 3) {
                for (int k=0; k<2; k++) {
                    x = cctv.pos[0];
                    y = cctv.pos[1];
                    while (x >= 0 && y >= 0 && x < N && y < M && newMap[x][y] != 6) {
                        if (newMap[x][y] == 0) newMap[x][y] = -1;
                        x += dx[(directions[i] + k) % 4];
                        y += dy[(directions[i] + k) % 4];
                    }
                }
            }
            else if (cctv.type == 4) {
                for (int k=0; k<3; k++) {
                    x = cctv.pos[0];
                    y = cctv.pos[1];
                    while (x >= 0 && y >= 0 && x < N && y < M && newMap[x][y] != 6) {
                        if (newMap[x][y] == 0) newMap[x][y] = -1;
                        x += dx[(directions[i] + k) % 4];
                        y += dy[(directions[i] + k) % 4];
                    }
                }
            }
            else {
                for (int k=0; k<4; k++) {
                    x = cctv.pos[0];
                    y = cctv.pos[1];
                    while (x >= 0 && y >= 0 && x < N && y < M && newMap[x][y] != 6) {
                        if (newMap[x][y] == 0) newMap[x][y] = -1;
                        x += dx[k];
                        y += dy[k];
                    }
                }
            }
        }

        getBlindSpot(newMap);
    }

    public static void getBlindSpot(int[][] newMap) {
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (newMap[i][j] == 0) cnt++;
            }
        }
        answer = Math.min(cnt, answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvs = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) cctvs.add(new CCTV(new int[]{i, j}, map[i][j]));
            }
        }

        directions = new int[cctvs.size()];
        DFS(0);
        System.out.println(answer);
    }
}
