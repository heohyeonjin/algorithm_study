import java.util.*;
import java.io.*;
class B15686{
    static int N,M;
    static int country[][];
    static boolean visited[];
    static ArrayList<int[]> all_chicken = new ArrayList<>();
    static ArrayList<int[]> houses = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        country = new int[N][N];

        for(int i=0;i<N;i++){
            input = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                country[i][j] = Integer.parseInt(input[j]);
                if(country[i][j]==2) all_chicken.add(new int[]{i,j});
                if(country[i][j]==1) houses.add(new int[]{i,j});
            }
        }
        visited = new boolean[all_chicken.size()];
        int remain_chicken[] = new int[M];
        dfs(0,0, remain_chicken);
        System.out.println(answer);
    }

    public static void dfs(int depth, int start, int[] remain_chicken){
        if(depth==M){ // 구해진 치킨집
            int result = eval_chicken_distance(remain_chicken); // 치킨 거리 구하기
            answer = Math.min(result,answer);
            return;
        }

        for(int i=start;i<all_chicken.size();i++){
            if(!visited[i]){
                remain_chicken[depth] = i;
                visited[i] = true;
                dfs(depth+1, i, remain_chicken);
                visited[i] = false;
            }

        }
    }

    public static int eval_chicken_distance(int[] remain_chicken){
        int sum = 0;
        for(int [] house : houses){
            int min_dis = Integer.MAX_VALUE;
            int h_row = house[0];
            int h_col = house[1];
            for(int chiken : remain_chicken){
                int c_row = all_chicken.get(chiken)[0];
                int c_col = all_chicken.get(chiken)[1];
               int dist =Math.abs(c_row-h_row)+Math.abs(c_col-h_col);
               min_dis = Math.min(dist,min_dis);
            }
            sum+=min_dis;
        }
        return sum;
    }
}