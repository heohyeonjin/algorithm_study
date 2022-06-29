package week04.P42748;

import java.util.Arrays;

// K 번째 수
public class P42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        int cnt = 0;
        for (int[] cmd : commands) {
            int[] temp = Arrays.copyOfRange(array, cmd[0] - 1, cmd[1]);
            Arrays.sort(temp);
            answer[cnt++] = temp[cmd[2] - 1];
        }
        return answer;
    }
}
