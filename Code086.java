import com.sun.source.tree.WhileLoopTree;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 */
public class Code086 {
    public ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode smallCur = smallDummy;
        ListNode bigDummy = new ListNode(0);
        ListNode bigCur = bigDummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val < x) {
                smallCur.next = cur;
                smallCur = cur;
                cur.next = null;
            }else {
                bigCur.next = cur;
                bigCur = cur;
                cur.next = null;
            }
            cur = next;
        }
        smallCur.next = bigDummy.next;
        return smallDummy.next;
    }

}
