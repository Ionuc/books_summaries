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

    /**
     * Problem 4.1
     * Implement a function to check if a tree is balanced. For the purposes of this question,
     * a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
     * from the root by more than one.
     */
    public static boolean isBalanced(BinaryTree root) {
        return getMaxDepth(root) - getMinDepth(root) <= 1;
    }

    private static int getMinDepth(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.min(getMinDepth(root.getLeft()), getMinDepth(root.getRight()));
    }

    private static int getMaxDepth(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getMaxDepth(root.getLeft()), getMaxDepth(root.getRight()));
    }

    /**
     * Problem 4.3
     * Given a sorted (increasing order) array, write an algorithm to create a binary tree with
     * minimal height.
     */
    public static BinaryTree createBinarySearchTree(Integer[] values) {
        return createBinarySearchTree(values, 0, values.length - 1);
    }

    private static BinaryTree createBinarySearchTree(Integer[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;
        BinaryTree binaryTree = new BinaryTree(values[middle],
                createBinarySearchTree(values, start, middle - 1),
                createBinarySearchTree(values, middle + 1, end));
        return binaryTree;
    }
}
