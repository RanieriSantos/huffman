package huffman;

import java.util.HashMap;

public class Compress {
        InputFile reader;
        HashMap<Character, Integer> map;

        public Compress(String input) {
                this.reader = new InputFile(input);
                map = new HashMap<Character, Integer>();
                map = reader.counter();
                reader.HashMapPrint();
        }

}