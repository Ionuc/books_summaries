package com.imesaros.thealgorithmdesignmanual.bst;

public class BinarySearchTree<T> {

    private T item;
    private BinarySearchTree<T> parent;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public BinarySearchTree<T> getParent() {
        return parent;
    }

    public void setParent(BinarySearchTree<T> parent) {
        this.parent = parent;
    }

    public BinarySearchTree<T> getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTree<T> left) {
        this.left = left;
    }

    public BinarySearchTree<T> getRight() {
        return right;
    }

    public void setRight(BinarySearchTree<T> right) {
        this.right = right;
    }
}
