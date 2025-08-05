package leetcode75;

import java.util.Stack;

/**
 * 在一个大小为 n 且 n 为 偶数 的链表中，对于 0 <= i <= (n / 2) - 1 的 i ，第 i 个节点（下标从 0 开始）的孪生节点为第 (n-1-i) 个节点 。
 * <p>
 * 比方说，n = 4 那么节点 0 是节点 3 的孪生节点，节点 1 是节点 2 的孪生节点。这是长度为 n = 4 的链表中所有的孪生节点。
 * 孪生和 定义为一个节点和它孪生节点两者值之和。
 * <p>
 * 给你一个长度为偶数的链表的头节点 head ，请你返回链表的 最大孪生和 。
 */
public class Code2130 {
    /**
     * 使用辅助结构实现
     *
     * @param head
     * @return
     */
    public int pairSum(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        int ans = 0;
        int index = 0;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
            index++;
        }
        int limit = index >> 1;
        cur = head;
        index = 0;
        while (index++ < limit) {
            ans = Math.max(ans, stack.pop().val + cur.val);
            cur = cur.next;
        }
        return ans;
    }

    /**
     * 不使用辅助结构实现
     * 翻转后半部分的链表
     *
     * @param head
     * @return
     */
    public int pairSum2(ListNode head) {
        ListNode cur = head;
        int ans = 0;
        int index = 0;
        while (cur != null) {
            cur = cur.next;
            index++;
        }
        int start = index >> 1;
        cur = head;
        index = 0;
        while (index++ < start - 1) {
            cur = cur.next;
        }
        // cur就是最后一个位置
        ListNode next = cur.next;
        ListNode pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 翻转链表之后 进行答案的计算
        ListNode cur1 = pre;
        cur = head;
        while (cur != null) {
            ans = Math.max(ans, pre.val + cur.val);
            pre = pre.next;
            cur = cur.next;
        }

        // 恢复链表
        pre = null;
        while (cur1 != null) {
            next = cur1.next;
            cur1.next = pre;
            pre = cur1;
            cur1 = next;
        }

        return ans;
    }

    public static void main(String[] args) {
        Code2130 code2130 = new Code2130();
        ListNode head = new ListNode(1);
        head.next = new ListNode(100000);
        int result = code2130.pairSum(head);
        System.out.println("最大孪生和: " + result); // 输出: 最大孪生和: 10
    }
}
