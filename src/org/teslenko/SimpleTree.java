package org.teslenko;

public class SimpleTree {
    private Node head = null;

    public void insert(int key) {
        Node newNode = new Node(key);
        if (head == null){
            head = newNode;
        } else{
            head.insertNode(newNode);
        }
    }
    public boolean hasValue(int key){
        if (head == null){
            return true;
        } else {
            return head.hasValue(key);
        }
    }


    private static class Node {

        public final int value;
        public Node right;
        public Node left;

        public Node(int value) {
            this.value = value;
        }

        public boolean hasValue(int key) {
            if (key == value) {
                return true;
            }
            if (key > value) {
                if (right != null) {
                    return right.hasValue(key);
                } else {
                    return false;
                }
            } else {

                if (left != null) {
                    return left.hasValue(key);
                } else {
                    return false;
                }
            }
        }

        public void insertNode(Node ins) {
            if (value > ins.value) {
                if (left != null) {
                    left.insertNode(ins);
                } else {
                    left = ins;
                }
            }
            if (value < ins.value) {
                if (right != null) {
                    right.insertNode(ins);
                } else {
                    right = ins;
                }
            }
        }
    }
}
