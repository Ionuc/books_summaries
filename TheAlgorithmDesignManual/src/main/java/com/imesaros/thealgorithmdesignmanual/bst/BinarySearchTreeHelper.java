package com.imesaros.thealgorithmdesignmanual.bst;


import java.util.Comparator;

public class BinarySearchTreeHelper {

    public <T> BinarySearchTree<T> search(BinarySearchTree<T> root, T item, Comparator<T> comparator) {
        if (root == null) {
            return null;
        }
        if (comparator.compare(root.getItem(), item) == 0) {
            return root;
        } else if (comparator.compare(root.getItem(), item) < 0) {
            return search(root.getLeft(), item, comparator);
        } else {
            return search(root.getRight(), item, comparator);
        }
    }
}
