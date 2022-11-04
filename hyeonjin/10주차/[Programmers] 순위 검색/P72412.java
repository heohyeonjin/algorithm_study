import java.io.*;
import java.util.*;
class P72412 {
    static HashMap<String,ArrayList<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) throws IOException{
        int[] answer = new int[query.length];

        //info에 대한 자료구조 정리
        info_align(info);

        // info의 점수 정렬
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }

        for(int i=0;i<query.length;i++){
            String tmp = query[i].replaceAll(" and ","");
            String[] q = tmp.split(" ");
            ArrayList<Integer> list = map.get(q[0]);


            if(list==null)
                answer[i] = 0;
            else {
                int left = 0; int right = list.size()-1;
                int score = Integer.parseInt(q[1]);
                int idx = binarySearch(list,left,right,score);
                answer[i] = idx;
            }
        }
        return answer;
    }
    public static void info_align(String[] info){
        for(int i=0;i<info.length;i++){
            String tmp[] = info[i].split(" ");

            map.compute("----", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));

            //3개
            map.compute(tmp[0]+tmp[1]+tmp[2]+"-", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute(tmp[0]+tmp[1]+"-"+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute(tmp[0]+"-"+tmp[2]+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute("-"+tmp[1]+tmp[2]+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));

            //2개
            map.compute(tmp[0]+tmp[1]+"--", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute(tmp[0]+"-"+tmp[2]+"-", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute(tmp[0]+"--"+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute("-"+tmp[1]+tmp[2]+"-", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute("-"+tmp[1]+"-"+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute("--"+tmp[2]+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));

            //1개
            map.compute("---"+tmp[3], (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute("--"+tmp[2]+"-", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute("-"+tmp[1]+"--", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
            map.compute(tmp[0]+"---", (k, v) -> v == null ? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));

            map.compute(tmp[0]+tmp[1]+tmp[2]+tmp[3], (k,v)-> v == null? new ArrayList<>() : v).add(Integer.parseInt(tmp[4]));
        }
    }

    public static int binarySearch(ArrayList<Integer> list, int left, int right, int score){
        //list에서 score보다 큰 점수들
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < score)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return list.size()-left;
    }
}

