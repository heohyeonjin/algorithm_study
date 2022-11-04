import java.io.*;
import java.util.*;
class B2212{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 센서의 개수
        int K = Integer.parseInt(br.readLine()); // 집중국의 개수

        String str[] = br.readLine().split(" ");
        int sensors[] = new int[N];
        for(int i=0;i<N;i++){
            sensors[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(sensors);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<N-1;i++){
            pq.offer(sensors[i+1]-sensors[i]);
        }

        for(int i=0;i<K-1;i++){
            pq.poll();
        }
        int answer = 0;
        while(!pq.isEmpty()){
            answer+=pq.poll();
        }
        System.out.println(answer);
    }
}