package huffman;

import java.util.HashMap;

/**
 * The {@code Compress} class implements functions to compress text files using the Huffman's coding
 * algorithm.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class Compress {
        HashMap<Character, Integer> map;

        /**
         * Default constructor
         * 
         * @param input_path path to the input file.
         */
        public Compress(String input_path) {
                InputFile reader = new InputFile(input_path);
                this.map = new HashMap<Character, Integer>(reader.writeBuffer());
                printHashMap();
        }

        /**
         * Print the hash map.
         */
        public void printHashMap() {
                this.map.entrySet().forEach(entry -> {
                        System.out.println(
                                        "[ " + entry.getKey() + " | " + entry.getValue() + "\t]");
                });
        }

        public HashMap<Character, Integer> getMap() {
                return map;
        }
}
