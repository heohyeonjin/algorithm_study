package week14.B12100;

import java.io.*;
import java.util.*;

// 2048 (easy)
public class B12100 {
    static int N;
    static int[][] map;
    static int[] direction = new int[5];
    static int answer = Integer.MIN_VALUE;

    static void DFS(int cur) {
        if (cur == 5) {
            answer = Math.max(answer, moveBlock());

        } else {
            for (int i=0; i<4; i++) {
                direction[cur] = i;
                DFS(cur + 1);
            }
        }
    }

    static int moveBlock() {
        int[][] newMap = new int[N][N];
        for (int i=0; i<N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, N);
        }

        for (int k=0; k<5; k++) {
            if (direction[k] == 0) {
                for (int i=0; i<N; i++) {
                    int idx = 0, block = 0;
                    for (int j = 0; j < N; j++) {
                        if (newMap[j][i] == 0) continue;
                        if (newMap[j][i] == block) {
                            newMap[idx - 1][i] = block * 2;
                            newMap[j][i] = 0;
                            block = 0;
                        } else {
                            block = newMap[j][i];
                            newMap[j][i] = 0;
                            newMap[idx++][i] = block;
                        }
                    }
                }
            }
            else if (direction[k] == 1) {
                for (int i = 0; i < N; i++) {
                    int idx = 0, block = 0;
                    for (int j = 0; j < N; j++) {
                        if (newMap[i][j] == 0) continue;
                        if (newMap[i][j] == block) {
                            newMap[i][idx - 1] = block * 2;
                            newMap[i][j] = 0;
                            block = 0;
                        } else {
                            block = newMap[i][j];
                            newMap[i][j] = 0;
                            newMap[i][idx++] = block;
                        }
                    }
                }
            }
            else if (direction[k] == 2) {
                for (int i = 0; i < N; i++) {
                    int idx = N - 1, block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (newMap[j][i] == 0) continue;
                        if (newMap[j][i] == block) {
                            newMap[idx + 1][i] = block * 2;
                            newMap[j][i] = 0;
                            block = 0;
                        } else {
                            block = newMap[j][i];
                            newMap[j][i] = 0;
                            newMap[idx--][i] = block;
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < N; i++) {
                    int idx = N - 1, block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (newMap[i][j] == 0) continue;
                        if (newMap[i][j] == block) {
                            newMap[i][idx + 1] = block * 2;
                            newMap[i][j] = 0;
                            block = 0;
                        } else {
                            block = newMap[i][j];
                            newMap[i][j] = 0;
                            newMap[i][idx--] = block;
                        }
                    }
                }
            }
        }

        int max = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) max = Math.max(newMap[i][j], max);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        DFS(0);
        System.out.println(answer);
    }
}
