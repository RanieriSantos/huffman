package huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * The {@code InputFile} class implements the necessary functions to read the
 * text from files and use it in the operations of compress/decompress.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class InputFile {
        private HashMap<Character, Integer> map_char; // Chars readed from file.
        private BufferedReader input; // Input file.

        /**
         * Default constructor
         * 
         * @param input_path path to the input file.
         */
        public InputFile(String input_path) {
                this.map_char = new HashMap<Character, Integer>();

                try {
                        this.input = new BufferedReader(new FileReader(input_path));
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                writeBuffer();
        }

        /**
         * Write content from buffer to the hash map.
         * 
         * @return reference to the hash map.
         */
        protected HashMap<Character, Integer> writeBuffer() {
                char[] c;
                String line;

                try {
                        while ((line = this.input.readLine()) != null) {
                                c = line.toCharArray();

                                for (char char_string : c) {
                                        if (this.map_char.containsKey(char_string)) {
                                                this.map_char.put(char_string, this.map_char.get(char_string) + 1);
                                        } else {
                                                this.map_char.put(char_string, 1);
                                        }
                                }

                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return this.map_char;
        }

        /**
         * Print the hash map.
         */
        protected void printHashMap() {
                this.map_char.entrySet().forEach(entry -> {
                        System.out.println("[ " + entry.getKey() + " | " + entry.getValue() + "\t]");
                });
        }

}
