package com.deerhunter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 210. Course Schedule II
 * Medium
 * <p>
 * 3312
 * <p>
 * 162
 * <p>
 * Add to List
 * <p>
 * Share
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
 * <p>
 * Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * <p>
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/2/20 16:55
 */
public class Topic210 {
    /**
     * 深度遍历
     */
    public static class Solution1 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 记录每个节点的出边集合
            List<Integer>[] outEdges = new ArrayList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                outEdges[i] = new ArrayList<>();
            }
            for (int[] prerequisite : prerequisites) {
                outEdges[prerequisite[0]].add(prerequisite[1]);
            }
            int[] res = new int[numCourses];
            int index = 0;
            // 节点状态，0 未搜索 1 搜索中 2 搜索完成
            int[] status = new int[numCourses];
            List<Integer> nodes = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                if (status[i] == 0) {
                    nodes.clear();
                    if (!dfs(outEdges, nodes, status, i)) {
                        return new int[0];
                    }
                    for (int node : nodes) {
                        res[index++] = node;
                    }
                }
            }
            return res;
        }

        /**
         * 深度遍历图
         *
         * @param outEdges
         * @param nodes
         * @param status
         * @param index
         * @return 是否存在拓扑排序
         */
        private boolean dfs(List<Integer>[] outEdges, List<Integer> nodes, int[] status, int index) {
            if (status[index] == 1) {
                return false;
            }
            if (status[index] == 2) {
                return true;
            }
            status[index] = 1;
            for (int edge : outEdges[index]) {
                if (!dfs(outEdges, nodes, status, edge)) {
                    return false;
                }
            }
            nodes.add(index);
            status[index] = 2;
            return true;
        }
    }

    /**
     * 广度遍历
     */
    public static class Solution2 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 记录每个节点的出边集合
            List<Integer>[] outEdges = new ArrayList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                outEdges[i] = new ArrayList<>();
            }
            int[] inDegree = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                outEdges[prerequisite[1]].add(prerequisite[0]);
                inDegree[prerequisite[0]]++;
            }
            int[] res = new int[numCourses];
            int size = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
            while (!queue.isEmpty()) {
                int node = queue.remove();
                res[size++] = node;
                for (int adjacentNode : outEdges[node]) {
                    inDegree[adjacentNode]--;
                    if (inDegree[adjacentNode] == 0) {
                        queue.add(adjacentNode);
                    }
                }
            }
            return size == numCourses ? res : new int[0];
        }
    }
}
