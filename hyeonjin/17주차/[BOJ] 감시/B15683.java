import java.io.*;
import java.util.*;
class CCTV{
    public int row;
    public int col;
    public int cctv;
    public int dir_cctv;

    public CCTV(int row, int col, int cctv, int dir_cctv){
        this.row = row;
        this.col = col;
        this.cctv = cctv;
        this.dir_cctv = dir_cctv;
    }
}
class B15683{
    static int N,M;
    static ArrayList<CCTV> CCTVs = new ArrayList<>();
    static Queue<int[]> queue = new LinkedList<>();
    static int dir_row[] = {-1,0,1,0};
    static int dir_col[] = {0,1,0,-1};
    static int answer = Integer.MIN_VALUE;
    static int permu[];
    static int expose = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int map[][] = new int[N][M];
        for(int i=0;i<N;i++){
            input = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(input[j]);
                if(map[i][j]==0) expose++;
                if(map[i][j]!=0 && map[i][j]!=6){
                    int dir_cctv;
                    if(map[i][j]==1) dir_cctv = 4;
                    else if(map[i][j]==2) dir_cctv = 2;
                    else if(map[i][j]==3) dir_cctv = 4;
                    else if(map[i][j]==4) dir_cctv = 4;
                    else dir_cctv = 1;
                    CCTVs.add(new CCTV(i,j,map[i][j],dir_cctv));
                }
            }
        }

        permu = new int[CCTVs.size()];
        dfs(0, CCTVs, map);
        System.out.println(expose-answer);
    }
    public static void dfs(int depth, ArrayList<CCTV> CCTVs, int map[][]){

        if(depth == CCTVs.size()){
            int copy_map[][] = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copy_map[i][j] = map[i][j];
                }
            }
            int result = gamsi(permu,copy_map);
            if(result!=-1){
                answer = Math.max(result,answer);
            }
            return;
       }
       for(int i=0;i<CCTVs.get(depth).dir_cctv;i++){
           permu[depth] = i;
           dfs(depth+1, CCTVs, map);
       }
    }

    public static int gamsi(int permu[],int copy_map[][]) {
        // 하나의 경우의 수 감시
        int result = 0;
        queue = new LinkedList<>();
        for (int i = 0; i < CCTVs.size(); i++) { // 감시 카메라 종류에 맞게 카운팅
            int direction = permu[i];
            if (CCTVs.get(i).cctv == 1) {
                queue.offer(new int[]{CCTVs.get(i).row, CCTVs.get(i).col,direction}); // 감시카메라 위치랑 뻗어나갈 방향 큐에 넣기
                result+=eval(copy_map);
            }
            else if (CCTVs.get(i).cctv == 2) {
//                if (permu[i] == 2 || permu[i] == 3) return -1;
                for (int j=0; j<4; j+= 2) { // 큐에 두 개 넣기
                    queue.offer(new int[]{CCTVs.get(i).row, CCTVs.get(i).col, (direction + j) % 4});
                }
                result += eval(copy_map);
            }
            else if (CCTVs.get(i).cctv == 3) {
                for (int j=0; j<2; j++) {
                    queue.offer(new int[]{CCTVs.get(i).row, CCTVs.get(i).col, (direction + j) % 4});
                }
                result += eval(copy_map);
            }
            else if (CCTVs.get(i).cctv == 4) {
                for(int j=0;j<3;j++){
                    queue.offer(new int[]{CCTVs.get(i).row,CCTVs.get(i).col, (direction+j)%4});
                }
                result += eval(copy_map);
            } else {
//                if (permu[i] != 0) return -1;
                for(int j=0;j<4;j++){
                    queue.offer(new int[]{CCTVs.get(i).row, CCTVs.get(i).col, (direction+j)%4});
                }
                result += eval(copy_map);
            }
        }
        return result;
    }

    public static int eval(int [][] copy_map){
            int count = 0;
            while(!queue.isEmpty()){
                int dir[] = queue.poll();
                int n_row = dir[0]+dir_row[dir[2]];
                int n_col = dir[1]+dir_col[dir[2]];

                if(n_row<0 || n_col<0 || n_row>N-1 || n_col>M-1) continue; // 범위 밖
                if(copy_map[n_row][n_col]==6) continue; // 벽인 경우
                else{
                    if(copy_map[n_row][n_col]!=-1 && copy_map[n_row][n_col]==0){
                        count++; // 감시
                        copy_map[n_row][n_col] = -1;
                    }
                    queue.offer(new int[]{n_row,n_col,dir[2]});
                }
            }
            return count;
        }
}