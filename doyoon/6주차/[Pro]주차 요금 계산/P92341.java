package week06.P92341;

import java.util.*;

class Info {
    public int totalTime = 0;
    public String time, status;

    public void setTotalTime(String curTime) {
        int outHour = Integer.parseInt(curTime.substring(0, 2));
        int outMin = Integer.parseInt(curTime.substring(3));
        int inHour = Integer.parseInt(time.substring(0, 2));
        int inMin = Integer.parseInt(time.substring(3));

        int total = 0;
        if (outMin < inMin) {
            outHour--;
            total += 60;
        }
        total += outMin - inMin;
        total += (outHour - inHour) * 60;

        this.totalTime += total;
        this.time = curTime;
    }
}

class P92341 {
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Info> map = new TreeMap<>();
        for (String temp : records) {
            String[] record = temp.split(" ");

            Info cur;
            if (record[2].equals("IN")) {
                cur = map.getOrDefault(record[1], new Info());
                cur.time = record[0];
                cur.status = record[2];
                map.put(record[1], cur);
            }
            else {
                cur = map.get(record[1]);
                cur.setTotalTime(record[0]);
                cur.status = record[2];
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (String car : map.keySet()) {
            if (map.get(car).status.equals("IN")) map.get(car).setTotalTime("23:59");

            int fee = fees[1];
            int total = map.get(car).totalTime - fees[0];
            while (total > 0) {
                fee += fees[3];
                total -= fees[2];
            }

            answer.add(fee);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}