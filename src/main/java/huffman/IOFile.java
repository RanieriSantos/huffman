package huffman;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.BitSet;

/**
 * The {@code InputFile} class implements the necessary functions to read the text from files and
 * use it in the operations of compress/decompress.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class IOFile {
        private HashMap<Character, Integer> map_char; // Chars readed from file.
        private BufferedReader input; // Input file.
        private char[] charsFromFile;

        /**
         * Default constructor
         * 
         * @param input_path path to the input file.
         */
        public IOFile(String input_path) {
                this.map_char = new HashMap<Character, Integer>();

                try {
                        this.input = new BufferedReader(new FileReader(input_path));
                        bufferToMap();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        protected void writeToFile(HashMap<Character, String> map_bin) throws IOException {
                BitSet bin_buffer = new BitSet();
                BufferedWriter symbol_table =
                                new BufferedWriter(new FileWriter("docs/symbol_table.edt"));
                BufferedWriter debug_compressed =
                                new BufferedWriter(new FileWriter("docs/debug_compressed.txt"));
                OutputStream compressed = new BufferedOutputStream(
                                new FileOutputStream("docs/compressed.edz"));

                // Write symbol table.
                for (HashMap.Entry<Character, String> entry : map_bin.entrySet()) {
                        symbol_table.append(entry.getKey() + entry.getValue() + '\n');
                }

                int num_bitsets = 0; // Number of bits
                // Write compressed file.
                for (char curr_char : this.charsFromFile) {
                        String curr_key = map_bin.get(curr_char);
                        debug_compressed.append(curr_key);

                        if (curr_key != null) {
                                for (int i = 0; i < curr_key.length(); i++) {

                                        bin_buffer.set(num_bitsets,
                                                        curr_key.charAt(i) - '0' > 0 ? true
                                                                        : false);
                                        num_bitsets++;
                                }
                        }
                }

                byte[] bits = new byte[num_bitsets];

                for (int i = 0; i < num_bitsets; i++) {
                        bits[i] = (byte) (bin_buffer.get(i) ? 1 : 0);
                }

                symbol_table.close();
                debug_compressed.close();
                compressed.write(bits);
                compressed.close();
        }

        /**
         * Write content from buffer to the hash map.
         * 
         * @return reference to the hash map.
         */
        protected HashMap<Character, Integer> bufferToMap() {
                String line;

                try {
                        while ((line = this.input.readLine()) != null) {
                                this.charsFromFile = line.toCharArray();

                                for (char char_string : this.charsFromFile) {
                                        if (this.map_char.containsKey(char_string)) {
                                                this.map_char.put(char_string,
                                                                this.map_char.get(char_string) + 1);
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
                        System.out.println(
                                        "[ " + entry.getKey() + " | " + entry.getValue() + "\t]");
                });
        }
}
