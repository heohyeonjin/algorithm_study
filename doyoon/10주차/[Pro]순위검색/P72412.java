package week10.P72412;

import java.util.*;

public class P72412 {
    static int[] answer;
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public void setInfo(String[] info) {
        for (String str : info) {
            String[] temp = str.split(" ");
            DFS(temp, "", 0);
        }

        for (String str : map.keySet()) {
            ArrayList<Integer> temp = map.get(str);
            Collections.sort(temp);
        }
    }

    public void DFS(String[] info, String infoString, int cnt) {
        if (cnt == 4) {
            ArrayList<Integer> arr = map.getOrDefault(infoString, new ArrayList<>());
            arr.add(Integer.parseInt(info[4]));
            map.put(infoString, arr);
        }
        else {
            DFS(info, infoString + info[cnt], cnt + 1);
            DFS(info, infoString + "-", cnt + 1);
        }
    }

    public void queryProcess(String[] query) {
        for (int i=0; i<query.length; i++) {
            String[] temp = query[i].split(" ");

            String q = "";
            for (int k=0; k<temp.length-1; k++) {
                if (!temp[k].equals("and")) q += temp[k];
            }

            answer[i] = getAnswer(q, Integer.parseInt(temp[temp.length-1]));
        }
    }

    public int getAnswer(String query, int score) {
        if (!map.containsKey(query)) return 0;

        ArrayList<Integer> arr = map.get(query);
        int start = 0, end = arr.size()-1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) < score) start = mid+1;
            else end = mid-1;
        }

        return arr.size() - start;
    }

    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        setInfo(info);
        queryProcess(query);
        return answer;
    }
}
