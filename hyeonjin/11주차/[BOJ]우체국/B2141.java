import java.util.*;
import java.io.*;
class Post implements Comparable<Post>{
    public int spot;
    public int freq;

    public Post(int spot, int freq){
        this.spot = spot;
        this.freq = freq;
    }

    @Override
    public int compareTo(Post o) {
        if(this.spot==o.spot) return this.freq-o.freq;
        return this.spot-o.spot;
    }
}
class B2141{
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Post post[] = new Post[N];
        long sum = 0; //전체 인구 수

        for(int i=0; i<N; i++){
            String str[] = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int f = Integer.parseInt(str[1]);
            post[i] = new Post(s,f);
            sum+=f; // 총 인구 수
        }

        Arrays.sort(post);

        long tmp = 0;
        for(Post x : post){
            tmp+= x.freq;
            if(tmp >= (sum+1)/2) { // 반올림
                System.out.println(x.spot);
                break;
            }
        }

    }
}