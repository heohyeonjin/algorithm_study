package week12.P60057;

import java.util.*;

// 문자열 압축
public class P60057 {
    public int solution(String s) {
        int answer = s.length();

        for (int i=1; i<=s.length()/2; i++) {
            answer = Math.min(answer, splitString(s, i));
        }

        return answer;
    }

    public int splitString(String s, int num) {
        String compression = "";

        int idx = 0;
        while (idx < s.length()) {
            String repeat = s.substring(idx, Math.min(idx + num, s.length()));
            int pointer = idx + num, cnt = 1;

            while (pointer < s.length()) {
                if (s.substring(pointer, Math.min(pointer + num, s.length())).equals(repeat)) {
                    pointer += num;
                    cnt++;
                }
                else break;
            }

            if (cnt != 1) compression += cnt;
            compression += repeat;
            idx = pointer;
        }

        return compression.length();
    }
}
