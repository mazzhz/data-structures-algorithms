import java.util.Set;

public interface TRAI_24_X5 {

    /**
     * One-in-three union.
     * Creates and returns a new set that contains those elements that
     * are contained in exactly one of the three input sets.
     * If no element fullfills the reuqirement, returns a new empty set.
     * Does not modify input sets.
     * @param s1    input set
     * @param s2    input set
     * @param s3    input set
     * @param <E>   element type
     * @return      result set
     */
    public <E> Set<E> oneInThree(Set<E> s1, Set<E> s2, Set<E> s3);

}