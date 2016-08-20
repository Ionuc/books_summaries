package com.imesaros.crackingthetechnicalinterview.part1.chapter4;

public class BinaryTreeHelper {

    /**
     * Problem 4.0
     */
    public static void insert(BinaryTree root, Integer value) {
        if (root == null) {
            root = new BinaryTree(value);
            return;
        }
        if (root.getItem() > value) {
            if (root.getLeft() == null) {
                root.setLeft(new BinaryTree(value));
            } else {
                insert(root.getLeft(), value);
            }
        } else {
            if (root.getRight() == null) {
                root.setRight(new BinaryTree(value));
            } else {
                insert(root.getRight(), value);
            }
        }
    }
}
