package week06.P42586;

import java.util.*;

class Job {
    int progress, speed;

    Job(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }

    public void setProgress() {
        this.progress += this.speed;
    }
}

class P42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();

        Queue<Job> queue = new LinkedList<>();
        for (int i=0; i<progresses.length; i++) {
            queue.add(new Job(progresses[i], speeds[i]));
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            if (queue.peek().progress < 100) {
                if (cnt != 0) {
                    answer.add(cnt);
                    cnt = 0;
                }
                for (Job o : queue) o.setProgress();
            }
            else {
                queue.poll();
                cnt++;
            }
        }
        answer.add(cnt);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}