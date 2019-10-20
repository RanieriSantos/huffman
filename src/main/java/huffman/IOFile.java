package huffman;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        private FileInputStream in;// Aux to reset buffer to the beginning.
        private BufferedReader input;// Input file.

        /**
         * Compress constructor
         * 
         * @param input_path path of input file.
         */
        public IOFile(String input_path) {
                try {
                        this.in = new FileInputStream(new File(input_path));
                        this.input = new BufferedReader(new InputStreamReader(this.in));
                        this.map_char = new HashMap<Integer, Integer>();
                        fileToMap();
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
        public IOFile(String input_path, String map, String output) throws IOException {
                try {
                        InputStream temp = new FileInputStream(input_path);
                        this.keyMap = new HashMap<String, Integer>();
                        symbolTableToMap(map);
                        recover(temp, output);

                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        /**
         * 
         * @param temp
         * @param output
         * @throws IOException
         */
        protected void recover(InputStream temp, String output) throws IOException {
                OutputStream decompressed = new BufferedOutputStream(new FileOutputStream(output));
                byte[] buffer = temp.readAllBytes();
                String letter = "";
                try {
                        for (byte b : buffer) {
                                letter += (byte) b;
                                if (this.keyMap.containsKey(letter)) {
                                        decompressed.write((int) (this.keyMap.get(letter)));
                                        letter = "";
                                }
                        }
                        letter = "";
                        temp.close();
                        decompressed.close();
                } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                }
        }

        /**
         * 
         * @param map_bin
         * @param compress
         * @param compressMap
         * @throws IOException
         */
        protected void writeToFile(HashMap<Integer, String> map_bin, String compress, String compressMap)
                        throws IOException {
                int charac;
                BitSet bin_buffer = new BitSet();
                BufferedWriter symbol_table = new BufferedWriter(new FileWriter(compressMap));
                OutputStream compressed = new BufferedOutputStream(new FileOutputStream(compress));

                // Write symbol table.
                for (HashMap.Entry<Integer, String> entry : map_bin.entrySet()) {
                        if (entry.getKey() == 10 || entry.getKey() == (int) ('\n')) {
                                symbol_table.append("EOF" + entry.getValue() + '\n');
                        } else if (entry.getKey() == 13) {
                                symbol_table.append("CR" + entry.getValue() + '\n');
                        } else {
                                symbol_table.append(Character.toString((char) (int) entry.getKey()) + entry.getValue()
                                                + '\n');
                        }
                }

                int num_bitsets = 0; // Number of bits
                // Write compressed file.
                while ((charac = this.input.read()) != -1) {
                        String curr_key = map_bin.get(charac);
                        if (curr_key != null) {
                                for (int i = 0; i < curr_key.length(); i++) {
                                        bin_buffer.set(num_bitsets, curr_key.charAt(i) - '0' > 0 ? true : false);
                                        num_bitsets++;
                                }
                        }
                }

                byte[] bits = new byte[num_bitsets];

                for (int i = 0; i < num_bitsets; i++) {
                        bits[i] = (byte) (bin_buffer.get(i) ? 1 : 0);
                }

                symbol_table.close();
                compressed.write(bits);
                compressed.close();
        }

        /**
         * Write content from buffer to the hash map.
         * 
         * @return reference to the hash map.
         */
        protected HashMap<Integer, Integer> fileToMap() {
                int charac;
                try {
                        while ((charac = this.input.read()) != -1) {
                                if (this.map_char.containsKey(charac)) {
                                        this.map_char.put(charac, this.map_char.get(charac) + 1);
                                } else {
                                        this.map_char.put(charac, 1);
                                }
                        }
                        this.in.getChannel().position(0);
                        this.input = new BufferedReader(new InputStreamReader(this.in));
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
                                if (line.contains("EOF")) { // End of line
                                        this.keyMap.put(line.substring(3), 10);
                                } else if (line.contains("CR")) { // Carriage return
                                        this.keyMap.put(line.substring(2), 13);
                                } else {
                                        this.keyMap.put(line.substring(1), (int) line.charAt(0));
                                }
                        }
                        input.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
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