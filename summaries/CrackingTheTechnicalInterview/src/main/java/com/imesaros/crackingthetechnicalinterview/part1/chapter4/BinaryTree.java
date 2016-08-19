package com.imesaros.crackingthetechnicalinterview.part1.chapter4;


public class BinaryTree {
    private Integer item;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(Integer item) {
        this(item, null, null);
    }

    public BinaryTree(Integer item, BinaryTree left, BinaryTree right) {
        this.item = item;
        this.left = left;
        this.right = right;
    }

    public Integer getItem() {
        return item;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
