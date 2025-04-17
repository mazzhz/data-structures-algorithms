import java.util.Iterator;
import java.util.NoSuchElementException;

public class TRAI_24_X7_marjussi<E> implements TRAI_24_X7<E> {


    /**
     * SELF-EVALUATION HERE:
     *Tehtävässä eniten aikaa vei ymmärtää, mikä kaikki pitää määritellä ja alustaa. Käytin linkitettyä rakennetta
     * sillä se tuntui itsestä luontevammalta. Mielestäni koodi näyttää ihan hyvältä, tekee niinkuin tehtävässä
     * pyydetään ja toimii hyvin
     *
     *Aikavaativuudet:
     * add(E x) - O(1), sillä se lisää jonon loppuun
     * remove - O(1), sillä se poistaa jonon alusta
     * isEmpty O(1), sillä se katsoo vain jonon koon
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    //konstruktori
    public TRAI_24_X7_marjussi(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an element to tail of the queue.
     *
     * @param x element to add
     * @return true
     */
    @Override
    public boolean add(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()){
            head = newNode;
        }else{
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    /**
     * Removes and returns the head of the queue
     *
     * @return element that was removed
     * @throws NoSuchElementException if queue was empty
     */
    @Override
    public E remove() {
        if (isEmpty()){
            throw new NoSuchElementException("Jono on tyhjä");
        }
        E element = head.data;
        head = head.next;

        if (head == null){
            tail = null;
        }
        size --;
        return element;
    }

    /**
     * Is queue empty or not?
     *
     * @return true if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
    return size == 0; //palauttaa toden, jos jono on tyhjä
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * This is for task 32.
     * But keep this when submitting task X7
     * It is not tested in X7.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
