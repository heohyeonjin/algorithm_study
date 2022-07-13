package week05.B14719;

import java.util.*;
import java.io.*;

public class B14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i=1; i<N-1; i++) {
            int cur = arr[i];
            int minLeft = cur;
            int minRight = cur;

            for (int j=0; j<i; j++) {
                if (arr[j] > cur) minLeft = Math.max(minLeft, arr[j]);
            }
            for (int j=i+1; j<N; j++) {
                if (arr[j] > cur) minRight = Math.max(minRight, arr[j]);
            }

            if (Math.min(minLeft, minRight) > cur)
                answer += Math.min(minLeft, minRight) - cur;
        }

        System.out.println(answer);
    }

}
