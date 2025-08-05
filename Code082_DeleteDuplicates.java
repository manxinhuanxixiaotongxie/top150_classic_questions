/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class Code082_DeleteDuplicates {
    /**
     * 官方题解
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 链表已经进行过排序
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            // 如果next的值与next next值相等 就把当前cur.next直接指向cur.next.next
            // 1 2 2 2 3   转换之后链表应该变成 1 3
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
