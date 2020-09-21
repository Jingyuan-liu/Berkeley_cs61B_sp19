public class OffByN implements CharacterComparator {

    private int n;

    public OffByN(int N) {
        this.n = N;
    }

    @Override
    public boolean equalChars(char A, char B) {
        return (Math.abs(A - B) == this.n);
    }
}
