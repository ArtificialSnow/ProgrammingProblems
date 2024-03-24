package problems.hard;

public class _25_ReverseNodesInKGroup {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;

        var dummyTail = new ListNode(0);
        var prevTail = dummyTail;

        outer:
        while (head != null) {
            var current = head;
            for (int i = 0; i < k; i++) {
                if (current == null) break outer;
                current = current.next;
            }

            var reversedTail = head;
            head = head.next;
            var prev = reversedTail;
            for (int i = 1; i < k; i++) {
                var next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            prevTail.next = prev;
            prevTail = reversedTail;
        }
        prevTail.next = head;
        return dummyTail.next;
    }
}
