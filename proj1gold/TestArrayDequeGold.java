/**
 * @source: StudentArrayDequeLauncher.java
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    String msg = "";
    @Test
    public void testGold(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (sad.isEmpty() && ads.isEmpty()) {
                if (numberBetweenZeroAndOne < 0.5) {
                    sad.addLast(i);
                    ads.addLast(i);
                    msg = msg +  "addLast(" + i + ")" + "\n";
                } else {
                    sad.addFirst(i);
                    ads.addFirst(i);
                    msg = msg +  "addFirst(" + i + ")" + "\n";;

                }

            } else {
                if (numberBetweenZeroAndOne < 0.25) {
                    sad.addLast(i);
                    ads.addLast(i);
                    msg = msg +  "addLast(" + i + ")" + "\n";
                } else if(numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne <0.5) {
                    sad.addFirst(i);
                    ads.addFirst(i);
                    msg = msg +  "addFirst(" + i + ")" + "\n";;

                } else if(numberBetweenZeroAndOne>=0.5 && numberBetweenZeroAndOne < 0.75) {
                    msg = msg + "removeFirst()" + "\n";
                    assertEquals(msg, sad.removeFirst(), ads.removeFirst());
                } else {
                    msg = msg + "removeLast()" + "\n";
                    assertEquals(msg, sad.removeLast(), ads.removeLast());
                }


            }
        }
    }
}
