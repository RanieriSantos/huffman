package huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.HashMap;

/**
 * The {@code InputFile} class implements the necessary functions to read the
 * text from files and use it in the operations of compress/decompress.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class IOFile {
        private HashMap<Integer, Integer> map_char; // Chars readed from file.
        private HashMap<String, Integer> keyMap;
        private InputStream input;
        // public BufferedReader input; // Input file.

        /**
         * Default constructor
         * 
         * @param input_path path of input file.
         */
        public IOFile(String input_path) {
                try {
                        this.input = new BufferedInputStream(new FileInputStream(input_path));
                        // this.input = new BufferedReader(new InputStreamReader(file));
                        this.map_char = new HashMap<Integer, Integer>();
                        bufferToMap();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        /**
         * Decompress constructor
         * 
         * @param input_path path of input file.
         * @param map        path of char map.
         */
        public IOFile(String input_path, String map) throws IOException {
                try {
                        InputStream temp = new FileInputStream(input_path);
                        this.keyMap = new HashMap<String, Integer>();
                        symbolTableToMap(map);
                        recover(temp);

                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        protected void recover(InputStream temp) throws IOException {
                OutputStream decompressed = new BufferedOutputStream(new FileOutputStream("docs/decompressed.txt"));
                String letter = "";
                int charac;
                try {
                        while ((charac = temp.read()) != -1) {
                                letter += charac;
                                if (this.keyMap.containsKey(letter)) {
                                        decompressed.write((int) (this.keyMap.get(letter)));
                                        letter = "";
                                } else if (Integer.valueOf(this.keyMap.get("\n")).toString().equals(letter)) {
                                        decompressed.write(10);
                                        letter = "";
                                }
                        }
                        temp.close();
                        decompressed.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        protected void writeToFile(HashMap<Integer, String> map_bin) throws IOException {
                int charac;
                BitSet bin_buffer = new BitSet();
                BufferedWriter symbol_table = new BufferedWriter(new FileWriter("docs/symbol_table.edt"));
                BufferedWriter debug_compressed = new BufferedWriter(new FileWriter("docs/debug_compressed.txt"));
                OutputStream compressed = new BufferedOutputStream(new FileOutputStream("docs/compressed.edz"));

                // Write symbol table.
                for (HashMap.Entry<Integer, String> entry : map_bin.entrySet()) {
                        int asc2 = entry.getKey();
                        symbol_table.append(Character.toString((char) asc2) + entry.getValue() + '\n');
                }

                int num_bitsets = 0; // Number of bits
                // Write compressed file.
                while ((charac = this.input.read()) != -1) {
                        // for (char curr_char : line.toCharArray()) {
                        String curr_key = map_bin.get(charac);
                        debug_compressed.append(curr_key);

                        if (curr_key != null) {
                                for (int i = 0; i < curr_key.length(); i++) {

                                        bin_buffer.set(num_bitsets, curr_key.charAt(i) - '0' > 0 ? true : false);
                                        num_bitsets++;
                                }
                        }
                        // }
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
        protected HashMap<Integer, Integer> bufferToMap() {
                this.input.mark(0);
                int charac;
                try {
                        while ((charac = this.input.read()) != -1) {
                                // for (char char_string : line.toCharArray()) {
                                if (this.map_char.containsKey(charac)) {
                                        this.map_char.put(charac, this.map_char.get(charac) + 1);
                                } else {
                                        this.map_char.put(charac, 1);
                                }
                                // }
                        }
                        this.input.reset();
                        // this.input = new BufferedReader(new InputStreamReader(this.file));
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return this.map_char;
        }

        /**
         * 
         * @param symbolTablePath Path to the file map of compressed file.
         * @return A HashMap of symbols.
         * @throws FileNotFoundException If input file not found.
         */
        protected void symbolTableToMap(String symbolTablePath) throws FileNotFoundException {
                BufferedReader input = new BufferedReader(new FileReader(symbolTablePath));
                String line;
                try {
                        while ((line = input.readLine()) != null) {
                                if (line.length() == 0) {
                                        // char temp = line.charAt(0);
                                        line = input.readLine();
                                        this.keyMap.put(String.valueOf('\n'), Integer.parseInt(line));
                                } else {
                                        int temp = line.charAt(0);
                                        this.keyMap.put(line.substring(1), temp);
                                }
                        }
                        input.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        protected void donePrint() {

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
