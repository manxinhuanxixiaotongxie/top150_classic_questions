/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 */
public class Code061 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        // 求链表的长度
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 取模
        int mod = k % len;
        // 有取模数据之后 意味着 总共需要有倒数第mod个节点需要移动到链表的头部
        // 假设链表的长度是5   q q q q q
        // 那么需要移动的位置就是从len - mod 到链表尾部
        // 找到这个位置的节点
        int move = len - mod;
        ListNode dummy = new ListNode(-1);
        cur = head;
        ListNode pre = null;
        while (move > 0) {
            ListNode next = cur.next;
            pre = cur;
            cur = next;
            move--;
        }
        // 此时 cur来到了新的头结点的位置
        if (pre == null) return head;
        pre.next = null;
        dummy.next = cur;
        cur = dummy;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 次数cur来到了新的尾结点的位置
        cur.next = head;
        return dummy.next;
    }
}
