import java.util.*;
class P92343 {
    public static ArrayList<Integer> graph[];
    public static int max_sheep = 0;
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        int n = info.length;
        graph = new ArrayList[n];
        for(int i=0;i<n;i++) graph[i] = new ArrayList<>();
        for(int i=0;i<edges.length;i++){
            int tmp[] = edges[i];
            int from = tmp[0];
            int to = tmp[1];
            graph[from].add(to);
        }

        ArrayList<Integer> next = new ArrayList<>();
        next.add(0);
        dfs(0,0,0,next,info);

        return max_sheep;
    }
    public static void dfs(int v, int sheep, int wolf, ArrayList<Integer> next,int info[]){
        if(info[v]==0) sheep++;
        else wolf++;

        ArrayList<Integer> c_next = (ArrayList<Integer>) next.clone();
        c_next.remove(new Integer(v));

        if(sheep>wolf){ // 유망한 노드이면
            max_sheep = Math.max(max_sheep,sheep); // 최대 양의 개수 갱신
            for(Integer x : graph[v]) c_next.add(x); // 다음에 이동할 노드 추가
            for(Integer x : c_next){ // 이동한 노드로 이동
                dfs(x, sheep, wolf, c_next, info);
            }
        }
        else return; // 유망하지 않은 경우

    }
}