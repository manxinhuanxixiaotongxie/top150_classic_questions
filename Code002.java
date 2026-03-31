/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 */
public class Code002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + add;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            add = value / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int value = l1.val + add;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            add = value / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = l2.val + add;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            add = value / 10;
            l2 = l2.next;
        }
        if (add != 0) {
            cur.next = new ListNode(add);
        }
        return dummy.next;
    }
}
