package huffman;

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

        public Decompress(String input_path, String map_path) throws IOException {
                this.iofile = new IOFile(input_path, map_path);
        }
}