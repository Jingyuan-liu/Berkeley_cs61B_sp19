public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        Deque dq = wordToDeque(word);
        return isPalindromeDq(dq);
    }

    private boolean isPalindromeDq(Deque dq) {
        if (dq.size() <= 1) {
            return true;
        } else {
            if (dq.removeFirst() == dq.removeLast()) {
                return isPalindromeDq(dq);
            }
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeCC(wordToDeque(word), cc);
    }

    private boolean isPalindromeCC(Deque dq, CharacterComparator cc) {
        if (dq.size() <= 1) {
            return true;
        } else {
            if (cc.equalChars((char) dq.removeFirst(), (char) dq.removeLast())) {
                return isPalindromeCC(dq, cc);
            }
            return false;
        }

    }




}
