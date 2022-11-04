import java.util.*;
class P42890 {
    static boolean visited[];
    static int n;
    static int i;
    static int answer =0;
    static ArrayList<String> unique_keys = new ArrayList<>();
    static String cur_key = "";
    public int solution(String[][] relation) {
        n = relation[0].length;
        visited = new boolean[n];
        for(int i=1;i<=n;i++) {
            findSet(0,i,relation); // i개의 조합
        }
        return answer;
    }
    public static void findSet(int start, int set_num, String [][] relation){
        if(set_num==0){
            if(unique_check(visited,relation)){ // 유일성 체크
                if(minimum_check(visited)){ // 최소성 체크
                    unique_keys.add(cur_key);
                    answer++;
                }
            }
            return;
        }
        for(int i=start;i<n;i++){
            visited[i] = true;
            findSet(i+1,set_num-1,relation);
            visited[i] = false;
        }
    }
    public static boolean unique_check(boolean visited[], String relation[][]){ // 유일성 체크
        List<String> keys = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();
        String key_idx_str = "";
        for(int i=0;i<visited.length;i++){
            if(visited[i]) {
                key_idx_str+=""+i+""; // 최소성 체크를 위함
                columns.add(i);
            }
        }

        for(int i=0;i<relation.length;i++){ // 각 행
            String tuple = "";
            for(int x : columns){
                tuple+=relation[i][x]+",";
            }
            if(keys.contains(tuple)) return false;
            else keys.add(tuple);
        }
        cur_key = key_idx_str; // 현재 후보키 (최소성 체크를 위함)
        return true;
    }

    public static boolean minimum_check(boolean visited[]){ // 최소성 체크 (03, 013인 경우)
        if(unique_keys.size()==0) return true;
        for(String x : unique_keys){
            char x_tmp[] = x.toCharArray();
            char cur_key_tmp[] = cur_key.toCharArray();
            int cnt =0;
            for(char a : x_tmp){
                for(char b : cur_key_tmp){
                    if(a==b) cnt++;
                }
            }
            if(x.length()==cnt) return false;
        }

        return true;
    }
}