package leetcode75;

/**
 * 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
 * <p>
 * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
 * <p>
 * 对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
 */
public class Code2095 {
    public ListNode deleteMiddle(ListNode head) {
        // 要删除的是下中点
        if (head == null || head.next == null) return null;
        ListNode slow = head.next;
        ListNode fast = head.next;
        ListNode pre = head;
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow
        pre.next = slow.next;
        return head;
    }
}
