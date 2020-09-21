package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(2);
        arb.enqueue(10);
        arb.enqueue(8);
        System.out.print(arb.dequeue());
        System.out.print(arb.dequeue());
        arb.enqueue(3);
        System.out.print(arb.dequeue());
    }
}
