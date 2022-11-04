import java.util.*;
class P42586{
    class Solution {

        public int[] solution(int[] progresses, int[] speeds) {
            int[] answer;
            ArrayList<Integer> ans = new ArrayList<>();
            int pro_len = progresses.length;
            int[] remain = new int[pro_len];
            for(int i=0;i<pro_len;i++){
                if((100-progresses[i])%speeds[i]==0) // 나누어 떨어지면
                    remain[i] = (100-progresses[i])/speeds[i];
                else{
                    remain[i] = (100-progresses[i])/speeds[i] +1;
                }
            }
            int cnt = 0;
            int start_idx = 0;
            for(int i=0;i<remain.length;i++){
                if(remain[start_idx]>=remain[i]){
                    cnt++;
                }
                else{
                    ans.add(cnt);
                    start_idx = i;
                    cnt = 0;
                    i--;
                }
            }

            if(cnt>0){
                ans.add(cnt);
            }

            answer = new int[ans.size()];
            int size = 0;
            for(Integer x : ans){
                answer[size++] = x;
            }
            return answer;
        }
    }
}