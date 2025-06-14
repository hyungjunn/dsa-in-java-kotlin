package list.linked;

import list.MyList;

public class MyLinkedList<E> implements MyList<E> {

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<E> temp = this;
            sb.append("[");
            while (temp != null) {
                sb.append(temp.item);
                if (temp.next != null) {
                    sb.append("->");
                }
                temp = temp.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }

    private Node<E> first;
    private int size = 0;

    @Override
    public void add(E e) {
        Node<E> node = new Node<>(e);
        if (first == null) {
            first = node;
        } else {
            Node<E> lastNode = getLastNode();
            lastNode.next = node;
        }
        size++;
    }

    private Node<E> getLastNode() {
        Node<E> current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void add(int index, E e) {
        Node<E> newNode = new Node<>(e);
        if (first == null) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<E> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> removeNode = getNode(index);
        E removedItem = removeNode.item;
        if (index == 0) {
            first = removeNode.next;
        } else {
            Node<E> prev = getNode(index - 1);
            prev.next = removeNode.next;
        }
        removeNode.item = null;
        removeNode.next = null;
        size--;
        return removedItem;
    }

    @Override
    public E set(int index, E e) {
        Node<E> x = getNode(index);
        E oldItem = x.item;
        x.item = e;
        return oldItem;
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index);
        return node.item;
    }

    private Node<E> getNode(int index) {
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public int indexOf(E o) {
        int index = 0;
        Node<E> current = first;
        while (current != null) {
            if (current.item.equals(o)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
               "first=" + first +
               ", size=" + size +
               '}';
    }
}
