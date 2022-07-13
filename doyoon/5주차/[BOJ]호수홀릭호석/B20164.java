package week05.B20164;

import java.util.*;
import java.io.*;

// 홀수 홀릭 호석
public class B20164 {
    public static int min, max;

    public static int getOdd(int N) {
        int num = 0;

        while (N > 0) {
            int curDigit = N % 10;
            if (curDigit % 2 != 0) num++;
            N /= 10;
        }
        return num;
    }

    public static void getFunc(int N, int cnt) {
        int odd = getOdd(N);

        if (N < 10) {
            min = Math.min(min, cnt + odd);
            max = Math.max(max, cnt + odd);
        }
        else if (N < 100) {
            getFunc(N % 10 + N / 10, cnt + odd);
        }
        else {
            String str = Integer.toString(N);
            for (int i=0; i<str.length()-2; i++) {
                for (int j=i+1; j<str.length()-1; j++) {
                    String s1 = str.substring(0, i + 1);
                    String s2 = str.substring(i + 1, j + 1);
                    String s3 = str.substring(j + 1);

                    int next = Integer.parseInt(s1) + Integer.parseInt(s2) + Integer.parseInt(s3);
                    getFunc(next, cnt + odd);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        getFunc(N, 0);
        System.out.println(min + " " + max);
    }
}
