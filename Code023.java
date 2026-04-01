import java.util.Comparator;
import java.util.PrimitiveIterator;
import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 */
public class Code023 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            ListNode poll =  pq.poll();
            if (poll.next != null) {
                ListNode next = poll.next;
                cur.next = poll;
                cur = cur.next;
                cur.next = null;
                pq.offer(next);
            }else {
                cur.next = poll;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
