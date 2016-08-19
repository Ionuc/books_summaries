package com.imesaros.crackingthetechnicalinterview.part1.chapter4;


import java.util.function.Consumer;

public final class BinaryTreeHelper {

    private BinaryTreeHelper() {
    }

    public static void traverInOrder(BinaryTree root, Consumer<Integer> consumer) {
        Travers.IN_ORDER.travers(root, consumer);
    }

    public static void traverPreOrder(BinaryTree root, Consumer<Integer> consumer) {
        Travers.PRE_ORDER.travers(root, consumer);
    }

    public static void traverPostOrder(BinaryTree root, Consumer<Integer> consumer) {
        Travers.POST_ORDER.travers(root, consumer);
    }

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

    private enum Travers {
        IN_ORDER {
            @Override
            void travers(BinaryTree root, Consumer<Integer> consumer) {
                if (root == null) {
                    return;
                }
                travers(root.getLeft(), consumer);
                consumer.accept(root.getItem());
                travers(root.getRight(), consumer);
            }
        },
        PRE_ORDER {
            @Override
            void travers(BinaryTree root, Consumer<Integer> consumer) {
                if (root == null) {
                    return;
                }
                consumer.accept(root.getItem());
                travers(root.getLeft(), consumer);
                travers(root.getRight(), consumer);
            }
        },
        POST_ORDER {
            @Override
            void travers(BinaryTree root, Consumer<Integer> consumer) {
                if (root == null) {
                    return;
                }
                travers(root.getLeft(), consumer);
                travers(root.getRight(), consumer);
                consumer.accept(root.getItem());
            }
        };

        abstract void travers(BinaryTree root, Consumer<Integer> consumer);
    }
}
