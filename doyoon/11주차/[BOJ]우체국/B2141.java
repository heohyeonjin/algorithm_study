package week11.B2141;

import java.util.*;
import java.io.*;

// 우체국
public class B2141 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] arr = new int[N][2];
        long sum = 0;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            sum += arr[i][1];
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        long mid = 0;
        for (int[] village : arr) {
            mid += village[1];
            if (mid >= (sum) / 2) {
                System.out.println(village[0]);
                break;
            }
        }
    }
}
