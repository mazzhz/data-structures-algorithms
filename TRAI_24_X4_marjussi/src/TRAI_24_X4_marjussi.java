import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public class TRAI_24_X4_marjussi implements TRAI_24_X4 {
    //                        ^^^^^
    //                   own userid here

    /**
     * SELF-EVALUATION HERE:
     *Tehtävä oli mielestäni todella hankala, sillä se vaati syvää ymmärrystä puun toiminnasta.
     * Minulla kesti kauan tajuta, että voin tehdä luurangossa olevalle metodille auttavan metodin, jonka sisällä
     * puu läpikäydään.
     *
     * aikavaativuus on O(n), missä n=puun solmujen lukumäärä
     */

    /**
     * Are the elements of a tree in in-order or not.
     * You may write additional methods as well as long as you keep this method header unchanged.
     * @param T binary tree to check
     * @return true if the elements are in in-order, false otherwise
     * @param <E> element type
     */
    @Override
    public <E extends Comparable<? super E>> boolean isInOrder(BTree<E> T) {
        // Aloitetaan kutsumalla puun T juurisolmua ja asetetaan minimi ja maksimiarvoiksi null
        return isInOrder(T.getRoot(), null, null);
    }
    //luodaan apumetodi, joka saa parametriksi solmun ja elementit minimi ja maksimi
    private <E extends Comparable<? super E>> boolean isInOrder(BTreeNode<E> node, E min, E max) {
        // jos solmu on tyhjä, palautetaan tosi. Tällöin puu on tyhjä
        if (node == null) {
            return true;
        }

        //asetetaan tutkittavan alkion arvoksi parametrina saadun solmun arvo
        E current = node.getElement();

        // Tarkistetaan, että solmun alkion arvot ovat minimin ja maksimin välillä
        if ((min != null && current.compareTo(min) <= 0) || (max != null && current.compareTo(max) >= 0)) {
            return false; //jos eivät ole, palautetaan false
        }

        // Katsotaan rekursiivisesti vasen ja oikea alipuu. Päivitetään väli, jolla tutkittavan
        //alkion arvo on tosi
        return isInOrder(node.getLeftChild(), min, current) &&  // asetetaan vasemman alipuun maksimirajaksi nykyisen
                                                                //solmun arvo (solmujen arvojen pitää olla vähemmän vasemmassa alipuussa)
                isInOrder(node.getRightChild(), current, max);   //asetetaan oikean alipuun minimirajaksi nykyisen
    }                                                           //solmun arvo (solmujen arvojen pitää olla enemmän oikeassa alipuussa)


}