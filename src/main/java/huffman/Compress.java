package huffman;

import java.util.HashMap;

/**
 * The {@code Compress} class implements functions to compress text files using the Huffman's coding
 * algorithm.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class Compress {
        protected HashMap<Character, Integer> map;
        protected PriorityQueue queue;
        protected BSTree tree;

        /**
         * Default constructor
         * 
         * @param input_path path to the input file.
         */
        public Compress(String input_path) {
                InputFile reader = new InputFile(input_path);
                this.map = new HashMap<Character, Integer>(reader.writeBuffer());
                this.tree = new BSTree(this.map);
                this.queue = new PriorityQueue(this.tree);
                startCompress();
        }

        private void startCompress() {
                merge();
        }

        private void merge() {
                while (this.queue.getSize() > 1) {
                        Node a = this.queue.poll();
                        Node b = this.queue.poll();
                        Node new_node = new Node(a, b);
                        this.queue.add(new_node);
                }
        }

        /**
         * Print the hash map.
         */
        public void print() {
                this.queue.print();
        }

        public HashMap<Character, Integer> getMap() {
                return map;
        }
}
