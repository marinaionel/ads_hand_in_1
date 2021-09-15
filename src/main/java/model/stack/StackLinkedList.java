package model.stack;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.logging.Logger;

public class StackLinkedList<T> extends Stack<T> {
    private Node root;
    private int size;

    public StackLinkedList() {
        size = 0;
        root = null;
    }

    public int size() {
        return size;
    }

    class Node {
        private T element;
        private Node successor;

        Node(T element) {
            this.setElement(element);
        }

        public Node getSuccessor() {
            return successor;
        }

        public void setSuccessor(Node successor) {
            this.successor = successor;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }

    public boolean empty() {
        return size() == 0;
    }

    public T push(T element) {
        Node newNode = new Node(element);

        if (root == null) root = newNode;
        else {
            Node temp = root;
            root = newNode;
            newNode.setSuccessor(temp);
        }
        Logger.getAnonymousLogger().info(element + " pushed to stack");
        size++;
        return element;
    }

    public synchronized T pop() {
        T popped;
        if (size() == 0) throw new EmptyStackException();
        popped = root.getElement();
        root = root.getSuccessor();
        size--;
        return popped;
    }

    public synchronized T peek() {
        if (size() == 0) throw new EmptyStackException();
        else return root.getElement();
    }

    public void clear() {
        while (size() > 0) pop();
    }
}
