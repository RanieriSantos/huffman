package huffman;

import java.io.FileNotFoundException;
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
                this.map = new IOFile(map).symbolTableToMap(map);
        }

}