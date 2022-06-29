package week04.P42840;

import java.util.ArrayList;
import java.util.Map;

public class P42840 {
    public int[] solution(int[] answers) {
        ArrayList<String> pattern = new ArrayList<>();
        pattern.add("12345");
        pattern.add("21232425");
        pattern.add("3311224455");

        int[] correct = new int[3];
        for (int i=0; i<answers.length; i++) {
            for (int j=0; j<3; j++) {
                if (Character.getNumericValue(pattern.get(j).charAt(i%pattern.get(j).length())) == answers[i]) {
                    correct[j]++;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int max = Math.max(Math.max(correct[0], correct[1]), correct[2]);
        if (correct[0] == max) list.add(1);
        if (correct[1] == max) list.add(2);
        if (correct[2] == max) list.add(3);

        return list.stream().mapToInt(i->i.intValue()).toArray();
    }
}
