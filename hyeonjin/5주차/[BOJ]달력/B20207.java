import java.io.*;
import java.util.*;

//class Plan implements Comparable<Plan>{
//    public int start;
//    public int end;
//    public Plan(int start, int end){
//        this.start = start;
//        this.end = end;
//    }
//    public int compareTo(Plan o){
//        int o_sub = o.end-o.start;
//        int this_sub = this.end-this.start;
//
//        if(this.start == o.start)
//            return o_sub-this_sub;
//        else
//            return this.start-o.start;
//    }
//}
class B20207{
//    static Plan plan[];
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
//        plan = new Plan[N];
        for(int i=0;i<N;i++){
            String str[] = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            for(int j=start;j<=end;j++){
                map.put(j,map.getOrDefault(j,0)+1);
            }
        }
//        Arrays.sort(plan);
        int result = Eval();
        System.out.println(result);
    }
    public static int Eval(){
        int result = 0;
        // 해쉬 맵 선언
//        HashMap<Integer,Integer> map = new HashMap<>();
//        // 캘린더 값 넣기
//        for(Plan p : plan){
//            for(int i=p.start;i<=p.end;i++){
//                map.put(i,map.getOrDefault(i,0)+1);
//            }
//        }
        Object firstKey = map.keySet().toArray()[0];
        int prevKey = (int) firstKey-1;
        int width=0;
        int height=0;
        // 계산
        // key가 연속된 숫자가 안나오면 그 전까지가 한 block (result 값 계산)
        for(Map.Entry<Integer,Integer> entrySet : map.entrySet()){
            if(entrySet.getKey()==prevKey+1){ // 연속된 키면 가로,세로 갱신
                width+=1;
                if(height<entrySet.getValue()){ // height 최대값 적용
                    height = entrySet.getValue();
                }
            }
            else { // 연속된 키가 아니면 result 계산
                result+=width*height;
                width=1; height = entrySet.getValue(); // 가로, 세로 초기화
            }
            prevKey = entrySet.getKey();
        }
        result += width*height;
        return result;
    }
}