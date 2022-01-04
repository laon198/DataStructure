package tree;

import common.Position;

public interface Tree<E> {
    int size();
    boolean isEmpty();
    Position<E> getRoot();
    int getHeight();
    int getDepth(Position<E> pos);
    boolean isRoot(Position<E> pos);
    boolean isExternal(Position<E> pos);
    Position<E> getParent(Position<E> pos) throws IllegalArgumentException;
    Iterable<Position<E>> getChildren(Position<E> pos);
    int getChildrenNum(Position<E> pos);
}
