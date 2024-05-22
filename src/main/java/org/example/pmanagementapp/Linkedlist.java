package org.example.pmanagementapp;

import java.util.Iterator;

public class Linkedlist implements Iterable<Project> {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        Project project;
        Node next;
        Node prev;

        Node(Project project) {
            this.project = project;
        }
    }



    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.project);
            temp = temp.next;
        }
    }


    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void add(Project project) {
        Node newNode = new Node(project);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }




    public boolean remove(Project project) {
        Node temp = head;

        while (temp != null) {
            if (temp.project.equals(project)) {
                if (temp == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else {
                    temp.prev.next = temp.next;
                }
                if (temp == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    temp.next.prev = temp.prev;
                }
                length--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public Iterator<Project> iterator() {
        return new Iterator<Project>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Project next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                Project project = current.project;
                current = current.next;
                return project;
            }
        };
    }
}
