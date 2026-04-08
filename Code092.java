/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class Code092 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right || right == 1) {
            return head;
        }
        int index = 0;
        ListNode cur = head;
        ListNode begin = null;
        ListNode end = null;
        ListNode prev = null;
        ListNode pre = null;
        ListNode nextBegin = null;
        while (cur != null) {
            ListNode next = cur.next;
            index++;
            if (index == left) {
                begin = cur;
                pre = prev;
            } else if (index == right) {
                end = cur;
                nextBegin = next;
            }
            prev = cur;
            cur = next;
        }
        // 翻转链表
        ListNode reverse = reverse(begin, end);
        if (begin == head) {
            // 换头
            begin.next = nextBegin;
            return reverse;
        }else {
            // 不换头
            pre.next = reverse;
            begin.next = nextBegin;
        }
        return head;
    }

    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode pre = null;
        ListNode cur = begin;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    static void main() {
        Code092 code092 = new Code092();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = code092.reverseBetween(head, 2, 4);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
