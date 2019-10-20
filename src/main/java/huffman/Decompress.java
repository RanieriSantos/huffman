package huffman;

import java.io.IOException;

/**
 * The {@code Decompress} class implements functions to decompress text files
 * compressed usingthe Huffman's coding algorithm.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class Decompress {
        public Decompress(String input_path, String map_path, String output) throws IOException {
                IOFile iofile = new IOFile(input_path, map_path, output);
        }
}