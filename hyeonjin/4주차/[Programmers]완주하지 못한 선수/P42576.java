import java.io.*;
import java.util.*;
class P42576 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);

        int p_len = participant.length;
        int c_len = completion.length;
        if(participant[p_len-1].equals(completion[c_len-1])){ // 동명 이인
            for(int i=0;i<c_len;i++){
                if(!participant[i].equals(completion[i])){
                    answer+=participant[i];
                    break;
                }
            }
        }
        else{
            answer+=participant[p_len-1];
        }

        return answer;
    }
}