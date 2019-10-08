package huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class InputFile {
        protected HashMap<Character, Integer> map_char;
        protected BufferedReader input;
        protected String line;
        protected char[] c;

        public InputFile(String input_path) {
                this.map_char = new HashMap<Character, Integer>();
                try {
                        this.input = new BufferedReader(new FileReader(input_path));
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                counter();
        }

        protected HashMap<Character, Integer> counter() {
                try {
                        while ((this.line = this.input.readLine()) != null) {
                                c = line.toCharArray();
                                for (char char_string : this.c) {
                                        if (this.map_char.containsKey(char_string)) {
                                                this.map_char.put(char_string, this.map_char.get(char_string) + 1);
                                        } else {
                                                this.map_char.put(char_string, 1);
                                        }
                                }

                        }
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return this.map_char;
        }

}