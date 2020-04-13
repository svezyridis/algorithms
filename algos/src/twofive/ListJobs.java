package twofive;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

public class ListJobs {

    public static class Job {
        int startTime;
        int finishTime;

        public Job(int startTime, int finishTime) {
            this.startTime = startTime;
            this.finishTime = finishTime;
        }
    }

    public static final class byStartTime implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            if (o1.startTime > o2.startTime) return 1;
            else if (o1.startTime < o2.startTime) return -1;
            return 0;
        }
    }

    public static final class byFinishTime implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            if (o1.finishTime > o2.finishTime) return 1;
            else if (o1.finishTime < o2.finishTime) return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[4];
        int[] startTimes = new int[]{3, 3, 6,16};
        int[] finishTimes = new int[]{6, 4, 10,18};
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = new Job(startTimes[i], finishTimes[i]);
        }
        Arrays.sort(jobs, new byStartTime());
        int maxIdle = jobs[0].startTime - 1;
        int startTime = jobs[0].startTime;
        int maxProcessingTime = 0;
        Job previousJob = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            if (previousJob.finishTime < jobs[i].startTime - 1) {
                int processingTime = previousJob.finishTime - startTime + 1;
                if (processingTime > maxProcessingTime)
                    maxProcessingTime = processingTime;
                startTime = jobs[i].startTime;
                int idleTime = jobs[i].startTime - previousJob.finishTime - 1;
                if (idleTime > maxIdle)
                    maxIdle = idleTime;
            } else if (previousJob.finishTime < jobs[i].finishTime) {
                System.out.println("updating previous job");
                previousJob = jobs[i];
            }
        }
        int processingTime = previousJob.finishTime - startTime + 1;
        if (processingTime > maxProcessingTime)
            maxProcessingTime = processingTime;
        System.out.println(maxIdle + " " + maxProcessingTime);
    }
}
