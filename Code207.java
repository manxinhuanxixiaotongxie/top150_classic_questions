import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 *
 */
public class Code207 {
    /**
     * 图的拓扑排序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true; // 如果没有课程，直接返回true
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();

        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            graph.computeIfAbsent(prerequisites[i][1], _ -> new ArrayList<>()).add(prerequisites[i][0]);
            inMap.put(prerequisites[i][0], inMap.getOrDefault(prerequisites[i][0],0) + 1);
            inMap.put(prerequisites[i][1], inMap.getOrDefault(prerequisites[i][1],0));
        }

        Queue<Integer> queue = new LinkedList<>();
        // 找到所有入度为0的点
        for (Map.Entry<Integer, Integer> entry : inMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        int nodes = 0;
        while (!queue.isEmpty()) {
            nodes++;
            List<Integer> next = graph.get(queue.poll());
            if (next != null && !next.isEmpty()) {
                for (Integer node : next) {
                    inMap.put(node, inMap.get(node) - 1);
                    if (inMap.get(node) == 0) {
                        queue.add(node);
                    }
                }
            }
        }
        return nodes == inMap.size();
    }
}
