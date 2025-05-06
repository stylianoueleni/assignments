import java.util.*;
/**
 * Write a description of class LZW here.
 *
 * Eleni Stylianou ge21708
 * December 2024
 */
public class LZW {
    public static void main(String[] args) {
        String s = "abracadabra";
        LZW lzw = new LZW();
        List<Integer> encoded = lzw.encode(s);
        System.out.println("Initial script: ");
        System.out.println(s);
        System.out.println("Encoded script: ");
        System.out.println(encoded);
        String decoded = lzw.decode(encoded);
        System.out.println("Decoded script: ");
        System.out.println(decoded);
    }

    /**
     * Encoding process using LZW.
     * @param s The input string to encode.
     * @return A list of integers representing the encoded data.
     */
    public List<Integer> encode(String s) {
        Map<String, Integer> dictionary = new HashMap<>();
        List<Integer> encoded = new ArrayList<>();

        // Initialize the dictionary with single characters.
        int dictSize = 256;
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(Character.toString((char) i), i);
        }

        String current = "";

        for (char c : s.toCharArray()) {
            String combined = current + c;
            if (dictionary.containsKey(combined)) {
                current = combined;
            } else {
                encoded.add(dictionary.get(current));
                dictionary.put(combined, dictSize++);
                current = Character.toString(c);
            }
        }

        // Output the code for the last pattern.
        if (!current.isEmpty()) {
            encoded.add(dictionary.get(current));
        }

        return encoded;
    }

    /**
     * Decoding process using LZW.
     * @param encoded A list of integers representing the encoded data.
     * @return The decoded string.
     */
    public String decode(List<Integer> encoded) {
        Map<Integer, String> dictionary = new HashMap<>();

        // Initialize the dictionary with single characters.
        int dictSize = 256;
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, Character.toString((char) i));
        }

        StringBuilder decoded = new StringBuilder();
        String previous = dictionary.get(encoded.get(0));
        decoded.append(previous);

        for (int i = 1; i < encoded.size(); i++) {
            int code = encoded.get(i);
            String current;
            if (dictionary.containsKey(code)) {
                current = dictionary.get(code);
            } else if (code == dictSize) {
                current = previous + previous.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid LZW code: " + code);
            }

            decoded.append(current);
            dictionary.put(dictSize++, previous + current.charAt(0));
            previous = current;
        }

        return decoded.toString();
    }
}
