/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 */
public class Code019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 如要删除的链表的长度是5 删除倒数第N个 那么要删除的节点的数量就是第5-N个节点
        if (head == null || n <= 0) return head;
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (n > len) return head;
        if (n == len) return head.next;
        // 不需要换头
        ListNode pre = null;
        cur = head;
        len = len - n;
        n = 0;
        while (cur != null) {
            ListNode next = cur.next;
            if ((n++) == len) {
                // 当前节点就是要删除的节点
                pre.next = next;
                cur.next = null;
                break;
            }
            pre = cur;
            cur = next;
        }
        return head;
    }

    /**
     * 极致解法
     * 一次遍历
     * <p>
     * 题目已经假定：
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 如要删除的链表的长度是5 删除倒数第N个 那么要删除的节点的数量就是第5-N个节点
        if (head == null || n <= 0) return head;
        // 有可能会换头 影子节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode left = dummyNode;
        ListNode right = dummyNode;
        // 快指针先走n步
        while (n-- > 0) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummyNode.next;
    }
}
