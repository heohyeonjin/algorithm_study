// 나무 위의 빗물
import java.io.*;
import java.util.*;
class B17073{
    static List<Integer>[] tree;
    static int N;
    static double W;
    static int cnt;
    ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str[] =  br.readLine().split(" ");
    N = Integer.parseInt(str[0]);
    W = Double.parseDouble(str[1]);
    tree = new ArrayList[N+1];
    for(int i=0;i<N+1;i++){
        tree[i] = new ArrayList<>();
    }
    for(int i=0;i<N-1;i++){ // 트리 생성
        str = br.readLine().split(" ");
        int v1 = Integer.parseInt(str[0]);
        int v2 = Integer.parseInt(str[1]);
        tree[v1].add(v2);
        tree[v2].add(v1);
    }
    double result = Solution();
        System.out.println(result);
    }
    public static double Solution(){
         for(int i=2;i<=N;i++){     //2일때부터 해야 맞음 1일때부터 하면 틀림.... (루트노트의 자식이 한개라면 루트도 리프로 인식하기 때문)
             if(tree[i].size()==1)
                 cnt++;
         }
       return W/(double)cnt;
    }
}