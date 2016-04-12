package com.esseckers.dragndropapp.linkedlist;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class ActorsLinkedList {
    private int size;
    private Node root;

    public ActorsLinkedList() {
        root = new Node(null, null, null);
        root.previous = root;
        root.next = root;
    }

    public Actor get(int location) {
        if (location >= 0 && location < size) {
            Node node = root;
            if (location < (size / 2)) {
                for (int i = 0; i <= location; i++) {
                    node = node.next;
                }
            } else {
                for (int i = size; i > location; i--) {
                    node = node.previous;
                }
            }
            return node.data;
        }
        return null;
    }

    public void add(Actor actor) {
        Node oldLast = root.previous;
        Node newNode = new Node(actor, oldLast, root);
        root.previous = newNode;
        oldLast.next = newNode;
        size++;
    }

    public boolean sort(int from, int to) {
        Actor actor = remove(from);
        if (to >= 0 && to <= size && actor != null) {
            Node node = root;
            if (to < (size / 2)) {
                for (int i = 0; i <= to; i++) {
                    node = node.next;
                }
            } else {
                for (int i = size; i > to; i--) {
                    node = node.previous;
                }
            }
            Node previous = node.previous;
            Node newNode = new Node(actor, previous, node);
            previous.next = newNode;
            node.previous = newNode;
            size++;
            return true;
        } else {
            return false;
        }
    }

    private Actor remove(int location) {
        if (location >= 0 && location < size) {
            Node node = root;
            if (location < (size / 2)) {
                for (int i = 0; i <= location; i++) {
                    node = node.next;
                }
            } else {
                for (int i = size; i > location; i--) {
                    node = node.previous;
                }
            }
            Node previous = node.previous;
            Node next = node.next;
            previous.next = next;
            next.previous = previous;
            size--;
            return node.data;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    private static final class Node {
        Actor data;

        Node previous, next;

        Node(Actor o, Node p, Node n) {
            data = o;
            previous = p;
            next = n;
        }
    }
}
