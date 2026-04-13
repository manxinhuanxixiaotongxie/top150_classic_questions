public class Code141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        // 判断一个链表是否有换
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != slow) {
            slow = slow.next;
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
        }
        return true;
    }
}
