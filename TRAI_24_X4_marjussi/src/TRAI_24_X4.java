import fi.uef.cs.tra.BTree;

public interface TRAI_24_X4 {

    /**
     * Are the elements of a tree in in-order or not.
     * @param T binary tree to check
     * @return true if the elements are in in-order, false otherwise
     * @param <E> element type
     */
    public <E extends Comparable<? super E>> boolean isInOrder(BTree<E> T);

}