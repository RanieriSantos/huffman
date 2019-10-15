package huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * The {@code Decompress} class implements functions to decompress text files
 * compressed usingthe Huffman's coding algorithm.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class Decompress {
        private IOFile iofile;
        private HashMap<Character, String> map;

        public Decompress(String input_path, String map) throws FileNotFoundException {
                this.iofile = new IOFile(input_path);
                this.map = new HashMap<Character, String>();
                symbolTableToMap(map);

        }

        private void symbolTableToMap(String map) throws FileNotFoundException {
                BufferedReader input = new BufferedReader(new FileReader(map));
                String line;
                try {
                        while ((line = input.readLine()) != null) {
                                this.map.put(line.charAt(0), line.substring(1));
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

       // private void 

        private void startDecompress() {

        }
}