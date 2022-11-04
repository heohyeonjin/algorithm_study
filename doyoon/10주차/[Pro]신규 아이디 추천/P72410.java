package week10.P72410;

// 신규 아이디 추천
public class P72410 {
    public String solution(String new_id) {
        // 1단계
        new_id = new_id.toLowerCase();

        // 2딘계
        String temp = "";
        for (char c : new_id.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.')
                temp += c;
        }

        // 3단계
        temp = temp.replaceAll("[.]+", ".");

        // 4단계
        if (!temp.isEmpty() && temp.charAt(0) == '.') temp = temp.substring(1);
        if (!temp.isEmpty() && temp.charAt(temp.length()-1) == '.') temp = temp.substring(0, temp.length()-1);

        // 5단계
        if (temp.isEmpty()) temp = "a";

        // 6단계
        if (temp.length() > 15) {
            temp = temp.substring(0, 15);
            if (temp.charAt(temp.length()-1) == '.') temp = temp.substring(0, 14);
        }

        // 7단계
        if (temp.length() < 3) {
            while (temp.length() != 3) temp += temp.charAt(temp.length()-1);
        }

        return temp;
    }
}
