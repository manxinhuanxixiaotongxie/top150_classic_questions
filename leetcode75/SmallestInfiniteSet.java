package leetcode75;

import java.util.TreeSet;

/**
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 * <p>
 * 实现 SmallestInfiniteSet 类：
 * <p>
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集中。
 */
public class SmallestInfiniteSet {
    /**
     * 思路参考了官解
     *
     * 如果使用一个集合或者数组来存储所有结构中存在的数，造成过大的内存的占用
     *
     */
    private int begin = 0;
    private TreeSet<Integer> set = null;

    public SmallestInfiniteSet() {
        // 初始化时，使用begin标记认为大于begin的数都在无限集合中
        begin = 1;
        // 使用treeset用来存储所有小于begin的数
        set = new TreeSet<>();
    }

    public int popSmallest() {
        int ans = 0;
        if (!set.isEmpty()) {
            return set.pollFirst();
        } else {
            ans = begin++;
            return ans;
        }
    }

    public void addBack(int num) {
        if (num < begin) {
            set.add(num);
        }
    }
}
