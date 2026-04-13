/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 */
public class Code148 {
    /**
     * 采用递归排序的思路实现O(n * log(n))的时间复杂度以及常数级空间复杂度
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return mergeSortListNode(head);
    }

    public ListNode mergeSortListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 归并排序
        // 找中点 上中点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow来到了中点位置
        // 从head到slow slow.next到结尾 进行归并排序
        ListNode next = slow.next;
        slow.next = null;
        ListNode left = mergeSortListNode(head);
        ListNode right = mergeSortListNode(next);
        return merge(left, right);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
                cur = cur.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
                cur = cur.next;
            }
        }
        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }

        return dummyHead.next;
    }

    static void main() {
        Code148 code148 = new Code148();
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode listNode = code148.sortList(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
