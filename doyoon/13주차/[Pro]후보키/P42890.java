package week13.P42890;

import java.util.*;

// 후보키
public class P42890 {
    public int answer = 0;
    public boolean[] keyValue;
    public HashSet<String> keySet = new HashSet<>();

    public int solution(String[][] relation) {
        int column = relation[0].length;
        keyValue = new boolean[column];

        getKey(0, column, relation);
        return answer;
    }

    public void getKey(int cnt, int column, String[][] relation) {
        if (cnt == column) {
            if (checkUnique(relation, column)) answer++;
            return;
        }

        keyValue[cnt] = false;
        getKey(cnt + 1, column, relation);
        keyValue[cnt] = true;
        getKey(cnt + 1, column, relation);
    }

    public boolean checkUnique(String[][] relation, int column) {
        String temp = "";
        for (int i=0; i<column; i++) {
            if (keyValue[i]) temp += i;
        }

        for (String str : keySet) {
            boolean flag = true;
            for (int i=0; i<str.length(); i++) {
                if (!temp.contains(str.substring(i, i+1))) flag = false;
            }
            if (flag) return false;
        }

        HashSet<String> set = new HashSet<>();
        for (String[] tuple : relation) {
            String info = "";
            for (int i=0; i<keyValue.length; i++) {
                if (keyValue[i]) info += tuple[i];
            }
            if (set.contains(info)) return false;
            set.add(info);
        }

        keySet.add(temp);
        System.out.println(keySet);
        return true;
    }
}
