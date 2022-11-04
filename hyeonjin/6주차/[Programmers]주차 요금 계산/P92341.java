import java.util.*;
class P92341{
    class Solution {
        public int[] solution(int[] fees, String[] records) {
            int[] answer;
            HashMap<String,String> curINInfo = new HashMap<>(); // 현재 차량 정보
            HashMap<String,Integer> time_sum = new HashMap<>(); // 차량 별 누계 시간

            for(int i=0;i<records.length;i++){
                String[] info = records[i].split(" ");
                if(info[2].equals("IN")) // IN 이면 차량 정보 저장
                    curINInfo.put(info[1],info[0]); // 차량 번호, 시각

                else { // OUT이면 누계 요금 부과
                    String INTime = curINInfo.get(info[1]); // 입차 시각
                    String OUTTime = info[0];
                    int out = ((OUTTime.charAt(0)-'0')*10+(OUTTime.charAt(1)-'0'))*60 + (OUTTime.charAt(3)-'0')*10+OUTTime.charAt(4)-'0';
                    int in =((INTime.charAt(0)-'0')*10+(INTime.charAt(1)-'0'))*60+(INTime.charAt(3)-'0')*10+INTime.charAt(4)-'0';
                    int time = out-in; // 주차 시간
                    time_sum.put(info[1],time_sum.getOrDefault(info[1],0)+time); // 차량 별 주차 시각 누적
                    curINInfo.remove(info[1]); //해당 해쉬 키 삭제
                }
            }
            // 출차하지 않은 차량 시간 계산
            String[] NoOut_carNums = curINInfo.keySet().toArray(new String[curINInfo.size()]);
            for(String key : NoOut_carNums){
                int out = 60*23+59;
                String INTime = curINInfo.get(key);
                int in =((INTime.charAt(0)-'0')*10+(INTime.charAt(1)-'0'))*60+(INTime.charAt(3)-'0')*10+INTime.charAt(4)-'0';
                int time = out-in;
                time_sum.put(key,time_sum.getOrDefault(key,0)+time);
            }

            // 누계 요금 정보
            Object[] mapkey = time_sum.keySet().toArray();
            Arrays.sort(mapkey);
            int answer_len = time_sum.size();
            answer = new int[answer_len];
            int i=0;
            for(Object x:mapkey) {
                answer[i++] = eval(time_sum.get(String.valueOf(x)), fees);
            }
            return answer;
        }
        public int eval(int time, int[] fees){
            if(time<=fees[0]){ // 기본 시간보다 작거나 같으면
                return fees[1];
            }
            else{ // 기본 시간 초과 했으면
                return (fees[1]+(int)(Math.ceil((time-fees[0])/(double)fees[2]))*fees[3]); // 나눌 때 (double)안하면 소수점 버리고 (int)형으로 바뀜
            }
        }
    }
}