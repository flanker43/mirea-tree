package org.teslenko;

public class SplayTree {
    private SplayNode root = null;

    public void insert(int ele) {
        SplayNode z = root;
        SplayNode p = null;
        while (z != null) {
            p = z;
            if (ele < p.element)
                z = z.right;
            else
                z = z.left;
        }
        z = new SplayNode();
        z.element = ele;
        z.parent = p;
        if (p == null)
            root = z;
        else if (ele < p.element)
            p.right = z;
        else
            p.left = z;
        Splay(z);
    }

    private void makeLeftChildParent(SplayNode c, SplayNode p) {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");

        if (p.parent != null) {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    private void makeRightChildParent(SplayNode c, SplayNode p) {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null) {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    private void Splay(SplayNode x) {
        while (x.parent != null) {
            SplayNode Parent = x.parent;
            SplayNode GrandParent = Parent.parent;
            if (GrandParent == null) {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            } else {
                if (x == Parent.left) {
                    if (Parent == GrandParent.left) {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    } else {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                } else {
                    if (Parent == GrandParent.left) {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    } else {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    public void remove(int ele) {
        SplayNode node = findNode(ele);
        Splay(node);
        if ((node.left != null) && (node.right != null)) {
            SplayNode min = node.left;
            while (min.right != null)
                min = min.right;

            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        } else if (node.right != null) {
            node.right.parent = null;
            root = node.right;
        } else if (node.left != null) {
            node.left.parent = null;
            root = node.left;
        } else {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
    }

    public boolean search(int val) {
        return findNode(val) != null;
    }

    private SplayNode findNode(int ele) {
        SplayNode z = root;
        while (z != null) {
            if (ele < z.element)
                z = z.right;
            else if (ele > z.element)
                z = z.left;
            else
                return z;
        }
        return null;
    }

    private static class SplayNode {
        public SplayNode left, right, parent;
        int element;

        public SplayNode() {
            this(0, null, null, null);
        }

        public SplayNode(int ele) {
            this(ele, null, null, null);
        }

        public SplayNode(int ele, SplayNode left, SplayNode right, SplayNode parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.element = ele;
        }
    }
}
