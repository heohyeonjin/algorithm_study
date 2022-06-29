import java.util.*;
class P42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0;i<commands.length;i++){
            int start = commands[i][0];
            int end = commands[i][1];
            int k = commands[i][2];
            int tmp [] = Arrays.copyOfRange(array,start-1,end);
            Arrays.sort(tmp);
            answer[i] = tmp[k-1];
        }
        return answer;
    }
}