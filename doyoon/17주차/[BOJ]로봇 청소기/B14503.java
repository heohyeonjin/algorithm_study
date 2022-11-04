package week17.B14503;

import java.io.*;
import java.util.*;

// 로봇 청소기
public class B14503 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int solution(int x, int y, int direction) {
        int answer = 0, cnt = 0;

        while (true) {
            if (!visit[x][y]) {
                answer++;
                visit[x][y] = true;
            }

            direction = (direction + 3) % 4;
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            cnt++;

            if (map[nx][ny] == 0 && !visit[nx][ny]) {
                x = nx;
                y = ny;
                cnt = 0;
                continue;
            }

            if (cnt == 4) {
                int back = (direction + 2) % 4;
                int backX = x + dx[back];
                int backY = y + dy[back];
                if (backX < 0 || backY < 0 || backX >= N || backY >= M || map[backX][backY] == 1) break;
                else {
                    x = backX;
                    y = backY;
                    cnt = 0;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] robot = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<3; i++) robot[i] = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(robot[0], robot[1], robot[2]));
    }
}
