import java.util.LinkedList;

public interface TRAI_24_X3 {


    /**
     * Add all elements to a sorted list.
     * LisÃ¤Ã¤ listaan A sellaiset B:n alkiot joita ei alkujaan ollut listassa A siten, ettÃ¤ A sÃ¤ilyy jÃ¤rjestyksessÃ¤.
     * @param A The list that will get new elements. In ascending order.
     * @param B The elements that will be added. In ascending order.
     * @param <E> element type
     */
    public <E extends Comparable<? super E>> void addAllSorted(LinkedList<E> A, LinkedList<E> B);

}