public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char A, char B) {
        int diff = Math.abs(A - B);
        return (diff == 1);
    }
}

