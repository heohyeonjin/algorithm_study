package week10.P72411;

import java.util.*;
import java.util.Map.*;

public class P72411 {
    public static int menu;
    public static String combiOrder;
    public static HashMap<String, Integer> map;

    public static void combi(String str, int cnt, int num) {
        if (num == menu) {
            map.put(combiOrder, map.getOrDefault(combiOrder, 0) + 1);
            return;
        }

        if (cnt == str.length()) return;

        combiOrder += str.charAt(cnt);
        combi(str, cnt+1, num+1);
        combiOrder = combiOrder.replace(String.valueOf(str.charAt(cnt)), "");
        combi(str, cnt+1, num);
    }

    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> temp = new ArrayList<>();

        for (int i=0; i<orders.length; i++) {
            char[] str = orders[i].toCharArray();
            Arrays.sort(str);
            orders[i] = new String(str);
        }

        for (int cnt : course) {
            menu = cnt;
            map = new HashMap<>();

            for (String order : orders) {
                combiOrder = "";
                combi(order, 0, 0);
            }

            int max = Integer.MIN_VALUE;
            for (Entry<String, Integer> entry : map.entrySet()) max = Math.max(max, entry.getValue());

            for (Entry<String, Integer> entry : map.entrySet()) {
                if(max >= 2 && entry.getValue() == max) temp.add(entry.getKey());
            }
        }

        Collections.sort(temp);

        String[] answer = new String[temp.size()];
        for (int i=0; i<temp.size(); i++) answer[i] = temp.get(i);

        return answer;
    }
}
