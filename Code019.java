/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 */
public class Code019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 如要删除的链表的长度是5 删除倒数第N个 那么要删除的节点的数量就是第5-N个节点
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
}
