import java.util.*;
import java.io.*;
class B3190{
    static int N;
    static boolean board[][];
    static HashMap<Integer,String> dir_change = new HashMap<>(); // 방향 변환 정보
    static HashMap<String,ArrayList<int[]>> change = new HashMap<>(); // 방향 변화 리스트
    static HashMap<String,String[]> head_set = new HashMap<>(); // 헤드 변환 세트
    static int head[] = {0,1}; // 오른쪽 방향
    static String head_dir = "RIGHT";
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new boolean[N+1][N+1];
        int K = Integer.parseInt(br.readLine());

        for(int i=0;i<K;i++){
            String str[] = br.readLine().split(" ");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);
            board[row][col] = true;
        }

        int L = Integer.parseInt(br.readLine());
        for(int i=0;i<L;i++){
            String str[] = br.readLine().split(" ");
            int sec = Integer.parseInt(str[0]);
            dir_change.put(sec,str[1]);
        }

        set_change_dir(); // 어디로 나아갈 지 설정
        set_change_head(); // 뱀의 머리 방향 설정
        solve();
        System.out.println(answer+1);
    }
    public static void solve(){
        ArrayList<int[]> bam = new ArrayList<>();
        bam.add(new int[]{1,1});
        int next_row = 1;
        int next_col = 1;

        while(true){
            int bam_size = bam.size();
            int tmp[] = bam.get(bam_size-1); // 뱀의 머리

            if(dir_change.containsKey(answer)) direction(); // 방향 변환

            // 특정 방향 지정
            next_row = tmp[0]+head[0];
            next_col = tmp[1]+head[1];

            if(!check(next_row,next_col,bam)) break; //벽 또는 몸과 부딪히는 경우

            if(!board[next_row][next_col]){ // 사과가 없으면
                bam.remove(0);
            }
            else board[next_row][next_col] = false;
            bam.add(new int[]{next_row,next_col});
            answer++;
        }
    }
    public static void direction(){
            String tmp = dir_change.get(answer);
            // head_dir 변환
            String[] str = head_set.get(head_dir);
            ArrayList<int[]> h = change.get(head_dir); // 오른쪽
            if(tmp.equals("L")){ // 왼쪽
                head[0]=h.get(1)[0];
                head[1]=h.get(1)[1];
                head_dir = str[1];
            }
            else{ //오른쪽
                head[0]=h.get(0)[0];
                head[1]=h.get(0)[1];
                head_dir = str[0];
            }
    }
    public static boolean check(int row, int col, ArrayList<int[]> bam){
        if(row>N || col>N || row<1 || col<1) // 벽에 부딪힌 경우
            return false;

        for(int[]x: bam){
            if(x[0]==row && x[1]==col) return false;
        }
        return true;
    }

    public static void set_change_dir(){
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(new int[]{1,0}); arr.add(new int[]{-1,0}); // 오른쪽에서 오른쪽, 왼쪽
        change.put("RIGHT",arr);
        arr = new ArrayList<>();
        arr.add(new int[]{0,-1}); arr.add(new int[]{0,1});
        change.put("DOWN",arr);
        arr = new ArrayList<>();
        arr.add(new int[]{-1,0}); arr.add(new int[]{1,0});
        change.put("LEFT",arr);
        arr = new ArrayList<>();
        arr.add(new int[]{0,1}); arr.add(new int[]{0,-1});
        change.put("UP",arr);

    }
    public static void set_change_head(){
      head_set.put("RIGHT",new String[]{"DOWN","UP"}); // 오른쪽, 왼쪽
      head_set.put("LEFT", new String[]{"UP","DOWN"});
      head_set.put("DOWN", new String[]{"LEFT","RIGHT"});
      head_set.put("UP", new String[]{"RIGHT","LEFT"});
    }
}