import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TRAI_24_X6_marjussi implements TRAI_24_X6 {
/**
 * Tehtävänanto oli todella sekava, joten sen ymmärtämiseen meni todella pitkä aika.
 * Tehtävä itsessään oli hankala, ja vaati tarkkuuta suunnitteluvaiheessa. Piti koko ajan
 * olla ajan tasalla siitä, mitä käsittelee (joukko, joukkojen joukko vai alijoukko) ja kun
 * kaikki termit kuulostavat samalta, oli tekeminen itselle aika kaaoottista ja sekavaa :D
 * Epävarmuutta tehtävän vastaukseen tuo se, ettei testiohjelma testannut koodin oikeellisuutta
 * en siis ole varma, toimiiko koodi kuten pitää, mutta testit näyttivät minun silmääni oikeilta.
 *
 *Aikavaativuus algoritmille on neliöllinen O(m^2 * n), missä m on syötejoukkojen määrä ja n on
 * alkioiden määrä
 *
 */

    /**
     * Computes which sets are subsets of each set.
     * The resulting mapping contains as a key each set Si of input SS and as the value
     * a set of all those sets Sj that are true subsets of Si.
     * @param SS input (set of sets)
     * @param <E> element type of sets
     * @return mapping showing the information which sets are subsets of each set
     */

    @Override
    public <E> Map<Set<E>, Set<Set<E>>> subSets(Set<Set<E>> SS) {
        Map<Set<E>, Set<Set<E>>> result = new HashMap<>();

        for (Set<E> Si : SS) {
            // Uusi joukko Si:n osajoukkojen tallentamiseksi
            Set<Set<E>> subsetsOfSi = new HashSet<>();

            //Käydään läpi ja verrataan joukkoja Si ja Sj joukkojen joukko SS:ssä
            for (Set<E> Sj : SS) {
                // Varmistetaan, ettei Sj ole sama joukko kuin Si ja tarkistetaan, onko Sj todellinen Si:n osajoukko
                if (Si != Sj && Si.containsAll(Sj)) {
                    // Jos Sj on Si:n osajoukko, lisätään se joukkoon subsetOfSi
                    subsetsOfSi.add(Sj);
                }
            }

            //Tallennetaan Si:n osajoukkojen kokoelma tulokseen
            result.put(Si, subsetsOfSi);
        }

        return result;
    }

}