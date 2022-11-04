package week15.B1092;

import java.util.*;
import java.io.*;

// 배
public class B1092 {
    static int N, M;
    static Integer[] crane;
    static ArrayList<Integer> box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        crane = new Integer[N];
        String input[] = br.readLine().split(" ");
        for (int i=0; i<N; i++) crane[i]= Integer.parseInt(input[i]);

        M = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        box = new ArrayList<>();
        for (int i=0; i<M; i++) box.add(Integer.parseInt(input[i]));

        Arrays.sort(crane, Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        System.out.println(solution());
    }

    public static int solution() {
        int answer = 0;

        while (!box.isEmpty()) {
            int size = box.size();
            for (int i=0; i<N; i++) {
                for (Integer curBox : box) {
                    if (curBox <= crane[i]) {
                        box.remove(curBox);
                        break;
                    }
                }
            }
            if (size == box.size()) return -1;
            answer++;
        }

        return answer;
    }
}
