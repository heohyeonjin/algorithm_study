package week13.P92343;

import java.util.*;

// 양과 늑대
public class P92343 {
    public int answer = 0;
    public ArrayList<ArrayList<Integer>> arr;

    public void DFS(int idx, int sheep, int wolf, ArrayList<Integer> next, int[] info) {
        if (info[idx] == 0) sheep++;
        else wolf++;

        if (sheep <= wolf) return;
        answer = Math.max(sheep, answer);

        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(next);
        list.remove(list.indexOf(idx));
        for (int i : arr.get(idx)) {
            list.add(i);
        }

        for (int i : list)
            DFS(i, sheep, wolf, list, info);
    }

    public int solution(int[] info, int[][] edges) {
        arr = new ArrayList<>();
        for (int i=0; i<info.length; i++) arr.add(new ArrayList<>());
        for (int[] edge : edges) arr.get(edge[0]).add(edge[1]);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        DFS(0, 0, 0, list, info);

        return answer;
    }
}
