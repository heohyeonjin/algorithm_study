package week13.P42888;

import java.util.*;

class P42888 {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        for (String str : record) {
            String[] info = str.split(" ");
            if (info[0].equals("Enter") || info[0].equals("Change")) map.put(info[1], info[2]);
        }

        ArrayList<String> arr = new ArrayList<>();
        for (String s : record) {
            String[] info = s.split(" ");
            String user = map.get(info[1]);

            if (info[0].equals("Enter")) arr.add(user + "님이 들어왔습니다.");
            else if (info[0].equals("Leave")) arr.add(user + "님이 나갔습니다.");
        }

        String[] answer = new String[arr.size()];
        for (int i=0; i<arr.size(); i++) answer[i] = arr.get(i);

        return answer;
    }
}
