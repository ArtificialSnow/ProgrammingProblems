package problems.medium;

import problems.hard._25_ReverseNodesInKGroup;

public class _24_SwapNodesInPairs {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        var dummy = new ListNode(0);
        var prevTail = dummy;
        while (head != null && head.next != null) {
            var second = head.next;
            var third = second.next;
            prevTail.next = second;
            second.next = head;
            prevTail = head;
            head = third;
        }
        prevTail.next = head;
        return dummy.next;
    }
}
