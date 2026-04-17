public class Code237 {
    /**
     * 这个题目是个抖机灵的题目 但是实际是不可行的
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
