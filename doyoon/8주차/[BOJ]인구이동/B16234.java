package week08.B16234;

import java.util.*;
import java.io.*;

// 인구 이동
public class B16234 {
    static int N, L, R;
    static int[][] people;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static boolean move() {
        boolean flag = false;

        boolean[][] check = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!check[i][j]) {
                    ArrayList<int[]> arr = new ArrayList<>();
                    queue.add(new int[]{i, j});
                    check[i][j] = true;

                    int sum = 0;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        sum += people[cur[0]][cur[1]];
                        arr.add(new int[] {cur[0], cur[1]});

                        for (int k=0; k<4; k++) {
                            int curX = cur[0] + dx[k];
                            int curY = cur[1] + dy[k];
                            if (curX < 0 || curY < 0 || curX >= N || curY >= N) continue;

                            int diff = Math.abs(people[curX][curY] - people[cur[0]][cur[1]]);
                            if (!check[curX][curY] && diff >= L && diff <= R) {
                                queue.add(new int[]{curX, curY});
                                check[curX][curY] = true;
                                flag = true;
                            }
                        }
                    }

                    if (arr.size() > 0) {
                        int aver = sum / arr.size();
                        for (int[] pos : arr) people[pos[0]][pos[1]] = aver;
                    }
                }
            }
        }

        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        people = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (move()) answer++;
        System.out.println(answer);

    }
}