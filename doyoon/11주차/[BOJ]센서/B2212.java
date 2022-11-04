package week11.B2212;

import java.util.*;
import java.io.*;

// 센서
public class B2212 {
    public static int solution(int N, int K, int[] arr) {
        Integer[] diff = new Integer[N-1];
        for (int i=0; i<N-1; i++) diff[i] = arr[i+1] - arr[i];

        int answer = 0;
        Arrays.sort(diff, Collections.reverseOrder());
        for (int i=K-1; i<N-1; i++) answer += diff[i];

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        System.out.println(solution(N, K, arr));
    }
}
