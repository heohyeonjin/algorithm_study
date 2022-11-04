package week16.B16236;

import java.util.*;
import java.io.*;

// 아기 상어
public class B16236 {
    static int answer = 0, cnt = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static int solution(int N, int[][] map, int[] pos) {
        int shark = 2;
        map[pos[0]][pos[1]] = 0;

        while (check(N, map, shark)) {
            pos = getNextPos(N, shark, pos, map);
            if (pos == null) break;
            if (cnt >= shark) {
                cnt-= shark;
                shark++;
            }
        }
        return answer;
    }

    public static boolean check(int N, int[][] map, int shark) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != 0 && map[i][j] < shark) return true;
            }
        }
        return false;
    }

    public static int[] getNextPos(int N, int shark, int[] pos, int[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(pos);

        boolean[][] visit = new boolean[N][N];
        visit[pos[0]][pos[1]] = true;

        int level = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i=0; i<len; i++) {
                int[] cur = queue.poll();
                if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < shark) {
                    map[cur[0]][cur[1]] = 0;
                    answer += level;
                    cnt++;
                    return cur;
                }
                for (int k=0; k<4; k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] > shark) continue;
                    if (!visit[nx][ny]) {
                        visit[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            level++;

            len = queue.size();
            PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1, o2) -> {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            });
            for (int i=0; i<len; i++) pqueue.offer(queue.poll());
            for (int i=0; i<len; i++) queue.offer(pqueue.poll());
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] pos = new int[2];
        int[][] arr = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        System.out.println(solution(N, arr, pos));
    }
}
