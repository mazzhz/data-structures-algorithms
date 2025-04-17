import java.util.Map;
import java.util.Set;

public interface TRAI_24_X6 {

    /**
     * Computes which sets are subsets of each set.
     * The resulting mapping contains as a key each set Si of input SS and as the value
     * a set of all those sets Sj that are true subsets of Si.
     * @param SS input (set of sets)
     * @param <E> element type of sets
     * @return mapping showing the information which sets are subsets of each set
     */
    public <E> Map<Set<E>, Set<Set<E>>> subSets(Set<Set<E>> SS);
}