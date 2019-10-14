package huffman;

import java.util.HashMap;

/**
 * The {@code Compress} class implements functions to compress text files using
 * the Huffman's coding algorithm.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class Compress {
        protected HashMap<Character, Integer> map;
        protected PriorityQueue queue;
        protected BSTree tree;
        // PriorityQueue heap;

        /**
         * Default constructor
         * 
         * @param input_path path to the input file.
         */
        public Compress(String input_path) {
                InputFile reader = new InputFile(input_path);
                this.map = new HashMap<Character, Integer>(reader.writeBuffer());
                this.tree = new BSTree(this.map);
                this.queue = new PriorityQueue(this.map.size());
                toQueue();
                merge();
                // printHashMap();
        }

        protected void toQueue() {
                while (this.tree.getRoot() != null) {
                        this.queue.add(tree.getRoot());
                        this.tree.delete(tree.getRoot());
                }
        }

        /**
         * Print the hash map.
         */
        public void printHashMap() {
                this.map.entrySet().forEach(entry -> {
                        System.out.println("[ " + entry.getKey() + " | " + entry.getValue() + "\t]");
                });
        }

        public HashMap<Character, Integer> getMap() {
                return map;
        }
}
