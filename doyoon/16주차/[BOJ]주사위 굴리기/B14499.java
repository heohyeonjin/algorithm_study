package week16.B14499;

import java.io.*;
import java.util.*;

// 주사위 굴리기
public class B14499 {
    static int N, M, curX, curY;
    static int[][] map;
    static int[] dice = new int[6];

    public static int move(int direction) {
        int temp = dice[0];
        switch (direction) {
            case 1:     // 동쪽
                if (curY + 1 >= M) return -1;
                curY++;
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
                break;
            case 2:     // 서쪽
                if (curY -1 < 0) return -1;
                curY--;
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                break;
            case 3:     // 북쪽
                if (curX -1 < 0) return -1;
                curX--;
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                break;
            case 4:     // 남쪽
                if (curX + 1 >= N) return -1;
                curX++;
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
                break;
        }

        if (map[curX][curY] == 0) {
            map[curX][curY] = dice[5];
        }
        else {
            dice[5] = map[curX][curY];
            map[curX][curY] = 0;
        }

        return dice[0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        curX = Integer.parseInt(st.nextToken());
        curY = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> answer = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<K; i++) {
            int top = move(Integer.parseInt(st.nextToken()));
            if (top != -1) answer.add(top);
        }

        for (int result : answer) System.out.println(result);
    }
}