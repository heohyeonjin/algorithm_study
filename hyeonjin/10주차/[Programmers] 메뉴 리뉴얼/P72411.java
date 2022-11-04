import java.io.*;
import java.util.*;
class P72411{
    static HashMap<String,Integer> map;
    static int max_value;
    static boolean visited[];
    public String[] solution(String[] orders, int[] course) throws IOException{
        ArrayList<String> result = new ArrayList<>();

        for(int i=0;i<course.length;i++){ //코스 개수 별
            map = new HashMap<>();
            max_value = Integer.MIN_VALUE;

            for(int j=0;j<orders.length;j++){
                visited = new boolean[orders[j].length()];
                char tmp[] = orders[j].toCharArray();
                Arrays.sort(tmp);
                String order = new String(tmp);
                findSet(0,course[i],order); // 주문 별 해쉬맵
            }

            for(String key : map.keySet()){
                Integer value = map.get(key);
                if(value == max_value){ // 최대 코스요리이면
                    if(max_value>1){
                        result.add(key);
                    }
                }
            }
        }
        String answer[] = new String[result.size()];
        int i=0;
        for(String x : result){
            answer[i++] = x;
        }

        Arrays.sort(answer);
        return answer;
    }

    // 특정 order, 특정 course 수
    public static void findSet(int start, int course_num, String order){
        // 알파벳 별 갯수 해시맵
        // 최대 value 값 리턴

        if(course_num==0){ // 조합
            String answer = "";
            for(int i=0;i<visited.length;i++){
                if(visited[i]){
                    answer+=order.charAt(i);
                }
            }
            map.put(answer,map.getOrDefault(answer,0)+1);
            if(max_value<map.getOrDefault(answer,0)+1){
                max_value = map.getOrDefault(answer,0);
            }
        }
        for(int i=start;i<order.length();i++){
            visited[i] = true;
            findSet(i+1,course_num-1,order);
            visited[i] = false;
        }

    }
}