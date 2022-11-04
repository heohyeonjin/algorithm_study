import java.util.*;
class P42888 {
    public String[] solution(String[] record) {
        ArrayList<String> result = new ArrayList<>();
        String answer[];
        HashMap<String,String> map = new HashMap<>();

        // 마지막 이름 설정
        int cnt=0;
        for(int i=0;i<record.length;i++){
            String str[] = record[i].split(" ");
            if(!str[0].equals("Change")) cnt++;
            if(str[0].equals("Leave")) continue;
            else {
                map.put(str[1],str[2]);
            }
        }
        answer = new String[cnt];
        int idx=0;
        for(int i=0;i<record.length;i++){
            String str[]=record[i].split(" ");
            String name = map.get(str[1]);
            if(str[0].equals("Enter")){
                answer[idx++] = name+"님이 들어왔습니다.";
            }
            else if(str[0].equals("Leave")){
                answer[idx++] = name+"님이 나갔습니다.";
            }
        }
        return answer;
    }
}