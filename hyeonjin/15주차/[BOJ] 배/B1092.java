import java.util.*;
import java.io.*;
class B1092{
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Integer crain[] = new Integer[N];
        String input[] = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            crain[i] = Integer.parseInt(input[i]);
        }
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> boxes = new ArrayList<>();
        input = br.readLine().split(" ");
        for(int i=0;i<M;i++){
            boxes.add(Integer.parseInt(input[i]));
        }

        Arrays.sort(crain,Collections.reverseOrder());
        Collections.sort(boxes,Collections.reverseOrder());
        System.out.println(solve(boxes,crain));

    }
    public static int solve(ArrayList<Integer> boxes, Integer crain[]){
        int answer = 0;
        while(!boxes.isEmpty()){
            int box_cnt = boxes.size();
            for(int i=0;i<N;i++){
                for(Integer W : boxes){
                    if(crain[i]>= W) {
                        boxes.remove(W);
                        break;
                    }
                }
            }
            if(box_cnt==boxes.size()) return -1;
            answer++;
        }
        return answer;
    }
}