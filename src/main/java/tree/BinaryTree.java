package tree;

import common.Position;

public interface BinaryTree<E> extends Tree<E> {
    Position<E> getLeft(Position<E> pos);
    Position<E> getRight(Position<E> pos);
    Position<E> getSibling(Position<E> pos) throws IllegalArgumentException;
    Iterable<Position<E>> preorder();
    Iterable<Position<E>> inorder();
    Iterable<Position<E>> postorder();
    Iterable<Position<E>> levelorder();
}
