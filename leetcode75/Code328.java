package leetcode75;

/**
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
 * <p>
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * <p>
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * <p>
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 */
public class Code328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 奇数节点
        ListNode odd = new ListNode(-1);
        ListNode oddHead = odd;
        // 偶数节点
        ListNode even = new ListNode(-1);
        ListNode evenHead = even; // 偶数节点的头节点
        ListNode cur = head;
        ListNode next;

        while (cur != null && cur.next != null) {
            next = cur.next.next;
            odd.next = cur; // 奇数节点
            odd = odd.next; // 移动到下一个奇数节点
            even.next = cur.next; // 偶数节点
            even = even.next; // 移动到下一个偶数节点
            odd.next = null;
            even.next = null;
            cur = next;
        }
        if (cur != null) {
            odd.next = cur; // 如果有奇数节点剩余，添加到奇数链表的末尾
            odd = odd.next; // 移动到下一个奇数节点
        }
        odd.next = evenHead.next;
        return oddHead.next;
    }
}
