import java.util.*;
import java.io.*;
class B13975{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int K = Integer.parseInt(br.readLine());
            String input[] = br.readLine().split(" ");
            long data[] = new long[K];
            for(int j=0;j<K;j++) data[j] = Integer.parseInt(input[j]);
            System.out.println(solve(data));
        }
    }
    public static long solve(long[] data){
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(long x: data) pq.offer(x);
        long sum = 0;
        while(pq.size()>1){
            long a = pq.poll();
            long b = pq.poll();
            sum +=(a+b);
            pq.offer(a+b);
        }
        return sum;
    }
}