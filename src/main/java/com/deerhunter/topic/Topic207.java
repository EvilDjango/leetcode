package com.deerhunter.topic;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 207. Course Schedule
 * Medium
 * <p>
 * 5140
 * <p>
 * 207
 * <p>
 * Add to List
 * <p>
 * Share
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/28 11:10
 */
public class Topic207 {
    /**
     * 深度遍历
     */
    public static class Solution1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, List<Integer>> edges = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                edges.putIfAbsent(prerequisite[0], new ArrayList<>());
                edges.get(prerequisite[0]).add(prerequisite[1]);
            }
            // 记录每个节点的状态， 0 未访问 1 拓展中 2 拓展完毕
            int[] status = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (status[i] == 2) {
                    continue;
                }

                if (findCircle(edges, status, i)) {
                    return false;
                }
            }
            return true;
        }

        private boolean findCircle(Map<Integer, List<Integer>> edges, int[] status, int i) {
            if (status[i] == 1) {
                return true;
            }
            if (status[i] == 2) {
                return false;
            }
            status[i] = 1;
            List<Integer> adjacentNodes = edges.getOrDefault(i, new ArrayList<>());
            for (int adjacentNode : adjacentNodes) {
                if (findCircle(edges, status, adjacentNode)) {
                    return true;
                }
            }
            status[i] = 2;
            return false;
        }
    }

    /**
     * 广度遍历
     */
    public static class Solution2 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> edges = new ArrayList<>();
            int[] inDegree = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                edges.add(new ArrayList<>());
            }
            for (int[] prerequisite : prerequisites) {
                edges.get(prerequisite[0]).add(prerequisite[1]);
                inDegree[prerequisite[1]]++;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
            int count = prerequisites.length;
            while (!queue.isEmpty()) {
                int node = queue.remove();
                for (int dest : edges.get(node)) {
                    inDegree[dest]--;
                    count--;
                    if (inDegree[dest] == 0) {
                        queue.add(dest);
                    }
                }
            }
            return count == 0;
        }
    }
}
