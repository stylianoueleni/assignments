import java.util.*;

/**
 * Write a description of class LZ78 here.
 *
 * Eleni Stylianou ge21708
 * December 2024
 */
public class LZ78 {
    public static void main(String[] args) {
        String s = "abracadabra";
        LZ78 lz78 = new LZ78();
        List<LZ78Pair> encoded = lz78.encode(s);
        System.out.println("Initial script: ");
        System.out.println(s);
        System.out.println("Encoded script: ");
        for (LZ78Pair pair : encoded) {
            System.out.println(pair);
        }
        String decoded = lz78.decode(encoded);
        System.out.println("Decoded script: ");
        System.out.println(decoded);
    }

    /**
     * Encoding process using LZ78.
     * @param s The input string to encode.
     * @return A list of LZ78 pairs.
     */
    public List<LZ78Pair> encode(String s) {
        Map<String, Integer> dictionary = new HashMap<>();
        List<LZ78Pair> encoded = new ArrayList<>();
        String current = "";
        int dictionaryIndex = 1;

        for (char c : s.toCharArray()) {
            current += c;
            if (!dictionary.containsKey(current)) {
                int prefixIndex = current.length() == 1 ? 0 : dictionary.get(current.substring(0, current.length() - 1));
                encoded.add(new LZ78Pair(prefixIndex, c));
                dictionary.put(current, dictionaryIndex++);
                current = "";
            }
        }

        if (!current.isEmpty()) {
            int prefixIndex = dictionary.getOrDefault(current.substring(0, current.length() - 1),0);
            encoded.add(new LZ78Pair(prefixIndex, current.charAt(current.length() - 1)));
        }

        return encoded;
    }

    /**
     * Decoding process using LZ78.
     * @param encoded A list of LZ78 pairs representing the encoded string.
     * @return The decoded string.
     */
    public String decode(List<LZ78Pair> encoded) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add(""); // Add an empty string at index 0
        StringBuilder decoded = new StringBuilder();

        for (LZ78Pair pair : encoded) {
            String prefix = dictionary.get(pair.getPrefixIndex());
            String entry = prefix + pair.getCharacter();
            decoded.append(entry);
            dictionary.add(entry);
        }

        return decoded.toString();
    }
}

/**
 * Class representing a pair for LZ78 encoding.
 */
class LZ78Pair {
    private final int prefixIndex;
    private final char character;

    public LZ78Pair(int prefixIndex, char character) {
        this.prefixIndex = prefixIndex;
        this.character = character;
    }

    public int getPrefixIndex() {
        return prefixIndex;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "(" + prefixIndex + ", " + character + ")";
    }
}
