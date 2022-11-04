package week15.B2812;

import java.io.*;
import java.util.*;

// 크게 만들기
public class B2812 {
    public static String solution(int N, int K, int[] origin) {
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(origin[0]);

        for (int i=1; i<N; i++) {
            while (!stack.isEmpty() && cnt < K && stack.peek() < origin[i]) {
                stack.pop();
                cnt++;
            }

            stack.push(origin[i]);
        }

        if (cnt < K) {
            for (int i=cnt; i<K; i++) stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N-K; i++) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();

        int[] origin = new int[N];
        for (int i=0; i<N; i++) origin[i] = Character.getNumericValue(num.charAt(i));
        System.out.println(solution(N, K, origin));
    }
}
