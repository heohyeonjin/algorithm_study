package week03.B14600;

import java.util.*;
import java.io.*;

// 샤워실 바닥깔기
public class B14600 {
    public static int[][] tile;
    public static int num = 1;

    public static boolean check(int x, int y, int len){
        int cur = tile[x][y];
        for (int i=x; i<x+len; i++) {
            for (int j=y; j<y+len; j++) {
                if (tile[i][j] != cur) return false;
            }
        }
        return true;
    }

    public static void fillNum(int x, int y) {
        for (int i=x; i<x+2; i++) {
            for (int j=y; j<y+2; j++) {
                if (tile[i][j] == 0) tile[i][j] = num;
            }
        }
        num++;
    }

    public static void L_Tromino(int x, int y, int len) {
        if (len == 2) fillNum(x, y);
        else {
            int curLen = len/2;
            if (check(x, y, curLen)) tile[curLen][curLen] = num;
            if (check(x, y+curLen, curLen)) tile[curLen][curLen+1] = num;
            if (check(x+curLen, y, curLen)) tile[curLen+1][curLen] = num;
            if (check(x+curLen, y+curLen, curLen)) tile[curLen+1][curLen+1] = num;
            num++;

            L_Tromino(x, y, curLen);
            L_Tromino(x, y+curLen, curLen);
            L_Tromino(x+curLen, y, curLen);
            L_Tromino(x+curLen, y+curLen, curLen);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int len = (int) Math.pow(2, K);
        tile = new int[len+1][len+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        tile[b][a] = -1;

        L_Tromino(1, 1, len);

        for (int i=len; i>0; i--) {
            for (int j=1; j<=len; j++) {
                System.out.print(tile[i][j] + " ");
            }
            System.out.println();
        }
    }
}
