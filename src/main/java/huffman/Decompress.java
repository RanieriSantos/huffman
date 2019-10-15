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
        private HashMap<Character, String> map;

        public Decompress(String input_path, String map_path) throws IOException {
                this.iofile = new IOFile(input_path);
                this.map = new IOFile(map_path).symbolTableToMap(map_path);
                startDecompressing();
        }

        private void startDecompressing() throws IOException {
                this.iofile.recover(this.iofile, this.map);
        }

        
}