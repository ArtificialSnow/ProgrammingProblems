package problems.medium;

public class _61_RotateList {
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

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        var originalHead = head;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }

        int rotation = k % count;
        if (count < 2 || rotation == 0) {
            return originalHead;
        }

        head = originalHead;
        for (int i = 1; i < count - rotation; i++) {
            head = head.next;
        }

        var newTail = head;
        var newHead = newTail.next;
        while (head.next != null) {
            head = head.next;
        }

        newTail.next = null;
        head.next = originalHead;
        return newHead;
    }
}
