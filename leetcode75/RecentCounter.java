package leetcode75;

import java.util.LinkedList;

/**
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 * <p>
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。
 * 确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 */
public class RecentCounter {
    LinkedList<Integer> linkedList = new LinkedList<Integer>();

    public RecentCounter() {

    }

    // [[],[642],[1849],[4921],[5936],[5957]]
    // [null,1,2,1,2,3]
    // [null,1,2,2,2,3]
    public int ping(int t) {
        if (linkedList.isEmpty()) {
            linkedList.addLast(t);
            return 1;
        } else {
            while (!linkedList.isEmpty() && linkedList.getFirst() < t - 3000) {
                linkedList.removeFirst();
            }
            linkedList.addLast(t);
            return linkedList.size();
        }
    }
}
