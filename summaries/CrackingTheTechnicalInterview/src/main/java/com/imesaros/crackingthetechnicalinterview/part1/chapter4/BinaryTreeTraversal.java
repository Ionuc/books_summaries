package com.imesaros.crackingthetechnicalinterview.part1.chapter4;


import java.util.function.Consumer;

public final class BinaryTreeTraversal {

    private BinaryTreeTraversal() {
    }

    /**
     * Problem 4.0
     */
    public static void traverInOrder(BinaryTree root, Consumer<Integer> consumer) {
        Travers.IN_ORDER.travers(root, consumer);
    }

    /**
     * Problem 4.0
     */
    public static void traverPreOrder(BinaryTree root, Consumer<Integer> consumer) {
        Travers.PRE_ORDER.travers(root, consumer);
    }

    /**
     * Problem 4.0
     */
    public static void traverPostOrder(BinaryTree root, Consumer<Integer> consumer) {
        Travers.POST_ORDER.travers(root, consumer);
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
