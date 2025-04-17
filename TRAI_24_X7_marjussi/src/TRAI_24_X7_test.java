import java.util.*;

public class TRAI_24_X7_test {

    public static void main(String[] args) {

        boolean ok = true;

        System.out.println("Create three queues:");

        TRAI_24_X7<Integer> queue1 = new TRAI_24_X7_marjussi<>();
        TRAI_24_X7<Integer> queue2 = new TRAI_24_X7_marjussi<>();
        TRAI_24_X7<String> queue3 = new TRAI_24_X7_marjussi<>();

        // create three comparison queues for expected results
        Queue<Integer> compare1 = new ArrayDeque<>();
        Queue<Integer> compare2 = new ArrayDeque<>();
        Queue<String> compare3 = new ArrayDeque<>();

        // should be empty initially
        ok &= testIsEmpty(queue1, compare1);

        System.out.println("Add to queue1 5 elements");
        ok &= testAdd(queue1, compare1, 5);

        System.out.println("Print queues (works after you have made toString).");
        System.out.println("queue1 :   " + queue1);
        System.out.println("Compare1 : " + compare1);

        ok &= testIsEmpty(queue1, compare1);

        System.out.println("Remove from queue1 4 elements");
        ok &= testRemove(queue1, compare1, 4);

        ok &= testIsEmpty(queue1, compare1);

        System.out.println("Add to queue1 20 elements");
        ok &= testAdd(queue1, compare1, 20);

        System.out.println("Remove queue1 21 elements");
        ok &= testRemove(queue1, compare1, 21);
        ok &= testIsEmpty(queue1, compare1);

        System.out.println("Test  remove from empty");
        if (queue1.isEmpty()) {
            try {
                queue1.remove();
                System.out.println("Should not have succeeded, but given an exception.");
                ok = false;
            } catch (NoSuchElementException e) {
                System.out.println("Got correct exception");
            } catch (Exception e) {
                System.out.println("Got wrong exception");
                ok = false;
            }
        } else {
            System.out.println("Queue was supposed to be empty.");
            ok = false;
        }

        System.out.println("Add to queue1 5 elements, them move them to queue 2 and remove from there");
        ok &= testAdd(queue1, compare1, 5);
        ok &= testMove(queue1, queue2, compare1, compare2, 5);
        ok &= testRemove(queue2, compare2, 5);

        System.out.println("Add and remove to/from queue3 25 strings");
        ok &= testAddString(queue3, compare3, 25);
        ok &= testRemove(queue3, compare3, 25);
        ok &= testIsEmpty(queue3, compare3);

        System.out.println("Add to queue2 5 elements");
        ok &= testAdd(queue2, compare2, 5);
        System.out.println("Circulate queue2 elements 87 times");
        ok &= circulate(queue2, compare2, 87);
        System.out.println("Print queues (shows after you have implemented toString).");
        System.out.println("Queue2: " + queue2);
        System.out.println("Compare2 : " + compare2);

        System.out.println("Remove from queue2 5 elements");
        ok &= testRemove(queue2, compare2, 5);
        ok &= testIsEmpty(queue2, compare2);

        System.out.println("\nAll tests before iteration: " + ok);

        // after you have implemented iterable, test it as well


        if (queue2 instanceof Iterable && queue2.iterator() != null) {

            System.out.println("\nTest iteration:");
            // don't call this defore your remove&isEmpty (or own clear works)
            queue2.clear();
            System.out.println("Add to queue2 25 elements");
            ok &= testAdd(queue2, compare2, 25);
            System.out.println("Circulate queue2 elements 87 times");
            ok &= circulate(queue2, compare2, 87);

            System.out.println("Print using iteration:");
            System.out.print("queue2 :  < ");
            for (Integer x : queue2)
                System.out.print(x + " ");
            System.out.println("< ");
            System.out.println("compare2 : " + compare2);

            System.out.println("\nTest concurrent modification exception:");

            Iterator<Integer> it = queue2.iterator();

            try {
                queue2.add(10);
                if (it.hasNext()) {
                    ;
                }
                System.out.println("Execution should not have entered here.");
                ok = false;

            } catch (ConcurrentModificationException ce) {
                System.out.println("Got correct ConcurrentModificationException");
            } catch (Exception e) {
                System.out.println("Got wrong Exception: " + e);
            }


        }

        System.out.println("\nAll tests result: " + ok);

    }

    static boolean testAdd(TRAI_24_X7<Integer> queue, Queue<Integer> comp, int n) {
        for (int i = 0; i < n; i++) {
            comp.add(i);
            if (! queue.add(i)) {
                System.out.println("add() returned false");
                return false;
            }
        }
        return true;
    }

    static boolean testAddString(TRAI_24_X7<String> queue, Queue<String> comp, int n) {
        for (int i = 0; i < n; i++) {
            comp.add("s" + i);
            if (!queue.add("s" + i)) {
                System.out.println("add() returned false");
                return false;
            };
        }
        return true;
    }



    static <E> boolean testRemove(TRAI_24_X7<E> queue, Queue<E> comp, int n) {
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            E v = comp.isEmpty() ? null : comp.remove();
            E x = null;
            try {
                x = queue.remove();
            } catch (NoSuchElementException e) {
                if (comp.isEmpty()) {
                    System.out.println("Got correct exception");
                    return true;
                } else {
                    System.out.println("Got wrong exception");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Got wrong exception");
                return false;
            }
            if (! Objects.equals(x, v)) {
                System.out.println("remove: wrong element:" + x + " expected:" +v);
                return false;
            }

        }
        return ok;
    }

    static <E> boolean testIsEmpty(TRAI_24_X7<E> queue, Queue<E> comp) {
        if (queue.isEmpty() == comp.isEmpty())
            return true;
        else {
            System.out.println("isEmpty wrong: isEmpty:" + queue.isEmpty() + " expected:" + comp.isEmpty());
            return false;
        }
    }

    static <E> boolean testMove(TRAI_24_X7<E> queue1, TRAI_24_X7<E> queue2,
                                Queue<E> comp1, Queue<E> comp2 , int n) {
        for (int i = 0; i < n; i++) {
            if (! comp1.isEmpty())
                comp2.add(comp1.remove());
            if (!queue1.isEmpty())
                queue2.add(queue1.remove());
        }
        return true;
    }

    static <E> boolean circulate(TRAI_24_X7<E> queue, Queue<E> comp, int n) {
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            E v = comp.remove();
            E x = queue.remove();
            if (! Objects.equals(x, v)) {
                System.out.println("remove: wrong element:" + x + " expected:" +v);
                ok = false;
            }
            comp.add(v);
            queue.add(x);
        }
        return ok;
    }





}
