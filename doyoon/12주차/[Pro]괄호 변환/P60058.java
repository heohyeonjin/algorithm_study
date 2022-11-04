package week12.P60058;

import java.util.*;

// 괄호 변환
public class P60058 {
    public String solution(String p) {
        return split(p);
    }

    public String split(String p) {
        if (p.length() == 0) return "";

        String u = "", v = "";
        int left = 0, right = 0;

        for (int i=0; i<p.length(); i++) {
            if (p.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                u = p.substring(0, i+1);
                v = p.substring(i+1);
                break;
            }
        }

        if (check(u)) return u + split(v);
        else {
            String result = "(" + split(v) + ")";

            for (int i=1; i<u.length()-1; i++) {
                if (u.charAt(i) == '(') result += ')';
                else result += '(';
            }
            return result;
        }
    }

    public boolean check(String u) {
        Stack<Character> stack = new Stack<>();

        for (char c : u.toCharArray()) {
            if (c == '(') stack.push(c);
            else if (stack.size() <= 0) return false;
            else stack.pop();
        }

        return stack.size() <= 0;
    }
}
