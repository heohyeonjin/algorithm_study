package week14.B3190;

import java.util.*;
import java.io.*;

// 뱀
public class B3190 {
    static int[][] map;
    static int N, curDirection = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Deque<int[]> deque = new ArrayDeque<>();

    public static boolean move() {
        int[] cur = deque.peek();
        int nx = cur[0] + dx[curDirection];
        int ny = cur[1] + dy[curDirection];

        if (nx < 1 || ny < 1 || nx >= N+1 || ny >= N+1) return false;

        for (int[] element : deque) {
            if (element[0] == nx && element[1] == ny) return false;
        }

        deque.addFirst(new int[]{nx, ny});
        if (map[nx][ny] != 1) deque.removeLast();
        else map[nx][ny] = 0;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        map = new int[N+1][N+1];
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        int[][] info = new int[L][2];
        for (int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);

            if (direction == 'D') info[i][1] = 1;
            else info[i][1] = -1;
        }

        int answer = 0, pointer = 0;
        deque.offer(new int[]{1, 1});

        while (true) {
            if (move()) answer++;
            else break;

            if (pointer < L && info[pointer][0] == answer) {
                if (info[pointer][1] == 1) curDirection = (curDirection + 1) % 4;
                else {
                    curDirection = (curDirection - 1) % 4;
                    if (curDirection == -1) curDirection = 3;
                }
                pointer++;
            }
        }

        System.out.println(answer + 1);
    }
}