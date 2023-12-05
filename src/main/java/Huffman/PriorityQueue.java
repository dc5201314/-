package Huffman;


public class PriorityQueue<T> {
    private Node<T> head;
    private int size;

    public void enqueue(T item, int priority) {
        Node<T> newNode = new Node<>(item, priority);

        if (head == null || priority < head.priority) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && priority >= current.next.priority) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("PriorityQueue is empty");
        }

        Node<T> node = head;
        head = head.next;
        size--;
        return node.item;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        private T item;
        private int priority;
        private Node<T> next;

        public Node(T item, int priority) {
            this.item = item;
            this.priority = priority;
        }
    }

}