public class ArrayDequeTest {

    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void testGet(){
        System.out.println("Running get test.");

        ArrayDeque<String> lld2 = new ArrayDeque<String>();
        lld2.addFirst("One");
        lld2.addLast("Two");
        lld2.addLast("Three");
        lld2.addLast("Four");
        System.out.println(lld2.get(0));
        System.out.println(lld2.get(1));
        System.out.println(lld2.get(2));
        System.out.println(lld2.get(3));
        System.out.println(lld2.get(4));
        System.out.println(lld2.get(-1));
    }

    public static void testDeepCopy(){
        System.out.println("Running deep copy test.");

        ArrayDeque<String> lld2 = new ArrayDeque<String>();
        lld2.addFirst("One");
        lld2.addLast("Two");
        lld2.addLast("Three");
        lld2.addLast("Four");
        ArrayDeque<String> lld3 = new ArrayDeque<>(lld2);
        lld3.printDeque();
        lld2.printDeque();
        lld2.removeLast();
        lld3.printDeque();
        lld2.printDeque();
        System.out.println(lld3.size());

    }

    public static void testResize(){
        System.out.println("Running resize test");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i < 19; i++){
            ad.addLast(i);
        }
        ad.printDeque();
    }

    public static void testShrink(){
        System.out.println("Running shrink test");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i < 19; i++){
            ad.addLast(i);
        }
        ad.printDeque();
        for (int i = 0; i < 15; i++){
            ad.removeFirst();
        }
        ad.printDeque();
    }

    public static void debug(){
        System.out.println("Running debug test");
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i < 153; i++){
            ad.addFirst(i);
            ad.printDeque();
        }
        for (int i = 0; i < 80; i++){
            System.out.println(ad.removeFirst());
        }
        for (int i = 0; i < 429; i++){
            ad.addFirst(i);
        }
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        testGet();
        testDeepCopy();
        testResize();
        testShrink();
        //debug();
    }
}
