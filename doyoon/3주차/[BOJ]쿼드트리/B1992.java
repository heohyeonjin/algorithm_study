package week03.B1992;

import java.util.*;
import java.io.*;

// 쿼드트리
public class B1992 {
    public static int[][] arr;

    public static boolean check0(int row, int col, int len) {
        for (int i=row; i<row + len; i++) {
            for (int j=col; j<col + len; j++) if (arr[i][j] != 0) return false;
        }
        return true;
    }

    public static boolean check1(int row, int col, int len) {
        for (int i=row; i<row + len; i++) {
            for (int j=col; j<col + len; j++) if (arr[i][j] != 1) return false;
        }
        return true;
    }

    public static String quadTree(int row, int col, int len) {
        if (check0(row, col, len)) return "0";
        if (check1(row, col, len)) return "1";

        String s1 = quadTree(row, col, len/2);
        String s2 = quadTree(row, col + len/2, len/2);
        String s3 = quadTree(row + len/2, col, len/2);
        String s4 = quadTree(row + len/2, col + len/2, len/2);
        return "(" + s1 + s2 + s3 + s4 + ")";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i=0; i<N; i++) {
            String temp = br.readLine();
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }

        System.out.println(quadTree(0, 0, arr.length));
    }
}
