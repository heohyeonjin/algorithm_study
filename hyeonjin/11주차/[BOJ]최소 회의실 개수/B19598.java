import java.io.*;
import java.util.*;
class Time implements Comparable<Time>{
    public int start;
    public int end;
    public Time(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        return this.start-o.start;
    }
}
class B19598{
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Time time[] = new Time[N];

        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(str.nextToken());
            int end = Integer.parseInt(str.nextToken());
            time[i] = new Time(start,end);

        }
        Arrays.sort(time);
        int answer = solve(time);
        System.out.println(answer);
    }
    public static int solve(Time time[]){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(Integer.MAX_VALUE);
        int cnt = 0;
        for(int i=0;i<time.length;i++){
            Integer tmp = pq.peek();
            if(tmp<=time[i].start){
                pq.poll();
            }
            else{
                cnt ++;
            }
            pq.offer(time[i].end);
        }
        return cnt;
    }
}