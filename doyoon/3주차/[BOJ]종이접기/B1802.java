package week03.B1802;

import java.util.*;
import java.io.*;

// 종이 접기
public class B1802 {
    public static String[] answer;

    public static void paper(String arr, int mid, int cnt) {
        if (mid == 0) {
            answer[cnt] = "YES";
            return;
        }

        for (int i=0; i<mid; i++) {
           if (arr.charAt(i) == arr.charAt(arr.length() - 1 - i)) {
                return;
            }
        }

        paper(arr.substring(0, mid), mid/2, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i=0; i<N; i++) arr[i] = br.readLine();

        answer = new String[N];
        Arrays.fill(answer, "NO");

        for (int i=0; i<N; i++) {
            int idx = arr[i].length() / 2;
            paper(arr[i], idx, i);
            System.out.println(answer[i]);
        }
    }
}
