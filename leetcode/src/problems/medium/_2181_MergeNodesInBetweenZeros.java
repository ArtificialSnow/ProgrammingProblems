package problems.medium;

public class _2181_MergeNodesInBetweenZeros {
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

    public ListNode mergeNodes(ListNode head) {
        head = head.next;

        var dummy = new ListNode(0);
        var current = dummy;
        var sum = 0;
        while (head != null) {
            if (head.val == 0) {
                current.next = new ListNode(sum);
                current = current.next;
                sum = 0;
            } else {
                sum += head.val;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
