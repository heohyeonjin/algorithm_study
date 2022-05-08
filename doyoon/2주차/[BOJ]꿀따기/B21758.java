package week02.B21758;

import java.util.*;
import java.io.*;

// 꿀 따기
public class B21758 {
    static int N, total = 0;
    static int[] arr;
    static int[] totalRight, totalLeft;

    public static int honey() {
        int answer = 0;

        totalLeft = new int[N];
        totalLeft[0] = 0;
        for (int i=1; i<N; i++) {
            totalLeft[i] = totalLeft[i-1] + arr[i-1];
        }

        totalRight = new int[N];
        totalRight[N-1] = 0;
        for (int i=N-2; i>=0; i--) {
            totalRight[i] = totalRight[i+1] + arr[i+1];
        }

        // 1. 벌 벌 벌통
        for (int i=1; i<N-1; i++) {
            int bee1 = total - arr[0] - arr[i];
            int bee2 = totalRight[i];
            answer = Math.max(answer, bee1 + bee2);
        }

        // 2. 벌통 벌 벌
        for (int i=N-2; i>0; i--) {
            int bee1 = total - arr[N-1] - arr[i];
            int bee2 = totalLeft[i];
            answer = Math.max(answer, bee1 + bee2);
        }

        // 3. 벌 벌통 벌
        for (int i=1; i<N-1; i++) {
            int bee1 = totalLeft[i] - arr[0] + arr[i];
            int bee2 = totalRight[i] - arr[N-1] + arr[i];
            answer = Math.max(answer, bee1 + bee2);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        System.out.println(honey());
    }
}
