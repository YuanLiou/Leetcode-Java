package com.inuker.solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by liwentian on 2016/12/17.
 */

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean[][] matrix = new boolean[numCourses][numCourses];
        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (!matrix[pre][cur]) {
                indegree[cur]++;
            }
            matrix[pre][cur] = true;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;

        int[] result = new int[numCourses];

        while (!queue.isEmpty()) {
            int course = queue.poll();

            result[count++] = course;

            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i]) {
                    matrix[course][i] = false;
                    if (--indegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }

        return count == numCourses ? result : new int[0];
    }
}
