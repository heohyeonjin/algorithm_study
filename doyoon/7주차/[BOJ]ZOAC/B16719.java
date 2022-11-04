package week07.B16719;

import java.util.*;
import java.io.*;

// ZOAC
class Char {
    int index;
    char c;

    Char(int index, char c) {
        this.index = index;
        this.c = c;
    }
}

public class B16719 {
    public static String str;
    public static Character[] answer;

    public static void ZOAC(List<Char> temp) {
        if (temp.isEmpty()) return;

        Char cur = temp.get(0);
        for (Char item : temp) {
            if (cur.c > item.c) {
                cur = item;
            }
        }

        answer[cur.index] = cur.c;
        for (int i=0; i<answer.length; i++) {
            if (answer[i] != null) System.out.print(answer[i]);
        }
        System.out.println();

        ZOAC(temp.subList(temp.indexOf(cur) + 1, temp.size()));
        ZOAC(temp.subList(0, temp.indexOf(cur)));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        ArrayList<Char> arr = new ArrayList<>();
        for (int i=0; i<str.length(); i++) arr.add(new Char(i, str.charAt(i)));

        answer = new Character[str.length()];
        ZOAC(arr);
    }
}
