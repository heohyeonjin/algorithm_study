import java.util.*;
import java.io.*;
class Data implements Comparable<Data>{
    public int index;
    public int L_R;
    public int x;

    public Data(int index, int L_R, int x){
        this.index = index;
        this.L_R = L_R;
        this.x = x;
    }

    @Override
    public int compareTo(Data o){
        return this.x - o.x;
    }
}
class B22942{
    static int N;
    static ArrayList<Data> input = new ArrayList<>();
    static String answer = "YES";
    static int prev_x=Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            String str[] = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int r = Integer.parseInt(str[1]);
            input.add(new Data(i,-1,x-r)); // left : -1
            input.add(new Data(i,1, x+r)); // right : 1
        }

        Collections.sort(input);
        solve();
        System.out.println(answer);
    }

    public static void solve(){
        Stack<Integer> stack = new Stack<>();

        for(Data x : input){
            if(x.x==prev_x){ // 겹치는 부분
                answer = "NO";
                return;
            }
            if(x.L_R==-1){
                stack.push(x.index);
            }
            else{
                if(stack.isEmpty()){
                    answer = "NO";
                    return;
                }
                else{
                    if(stack.pop()!=x.index){
                        answer = "NO";
                        return;
                    }
                }
            }
            prev_x = x.x;
        }


    }
}