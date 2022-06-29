package week02.B13164;

import java.util.*;
import java.io.*;

// 행복 유치원
public class B13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> diff = new ArrayList<>();
        for (int i=0; i<N-1; i++) {
            diff.add(arr[i+1] - arr[i]);
        }

        int answer = 0;
        Collections.sort(diff);
        for (int i=0; i<N-K; i++) answer += diff.get(i);

        System.out.println(answer);
    }
}
