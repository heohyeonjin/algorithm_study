package week04.P42576;

import java.util.HashMap;

// 완주하지 못한 선수
public class P42576 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();
        for (String str : participant) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        for (String str : completion) {
            map.put(str, map.get(str) - 1);
        }

        for (String str : map.keySet()) {
            if (map.get(str) != 0) answer = str;
        }

        return answer;
    }
}
