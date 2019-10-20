package huffman;

import java.io.IOException;
import java.util.HashMap;

/**
 * The {@code Compress} class implements functions to compress text files using
 * the Huffman's coding algorithm.
 * 
 * @author Ranieri Santos
 * @author imns1ght
 */
public class Compress {
        private IOFile iofile;
        private PriorityQueue heap;
        private HashMap<Integer, String> map;

        /**
         * Default constructor
         * 
         * @param input_path path to the input file.
         */
        public Compress(String input_path, String compress, String compressMap) {
                this.map = new HashMap<Integer, String>();
                this.iofile = new IOFile(input_path);
                HashMap<Integer, Integer> charCount = new HashMap<Integer, Integer>(iofile.fileToMap());
                BSTree charTree = new BSTree(charCount);
                this.heap = new PriorityQueue(charTree);
                startCompress(compress, compressMap);
        }

        private void startCompress(String compress, String compressMap) {
                mergeToTree();
                treeToMap(this.heap.peek(), "");
                try {
                        this.iofile.writeToFile(this.map, compress, compressMap);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void mergeToTree() {
                while (this.heap.getSize() > 1) {
                        Node a = this.heap.poll();
                        Node b = this.heap.poll();
                        Node new_node = new Node(a, b);
                        this.heap.add(new_node);
                }
        }

        private String treeToMap(Node node, String bin) {
                if (node != null) {
                        if (node.getLeft() == null) {
                                // If node is leaf.
                                this.map.put(node.getLetter(), bin);
                                bin = bin.length() > 0 ? bin.substring(0, bin.length() - 1) : ""; // bin--
                                return bin;
                        } else {
                                bin += '0';
                                bin = treeToMap(node.getLeft(), bin);
                        }

                        if (node.getRight() == null) {
                                // If node is leaf.
                                this.map.put(node.getLetter(), bin);
                                bin = bin.length() > 0 ? bin.substring(0, bin.length() - 1) : ""; // bin--
                                return bin;
                        } else {
                                bin += '1';
                                bin = treeToMap(node.getRight(), bin);
                        }
                }

                if (bin.length() > 0)
                        bin = bin.length() > 0 ? bin.substring(0, bin.length() - 1) : ""; // bin--

                return bin;
        }

        /**
         * Print the hash map.
         */
        public void print() {
                this.heap.print();
        }

        /**
         * Print the hash map.
         */
        protected void printHashMap() {
                this.map.entrySet().forEach(entry -> {
                        int temp = entry.getKey();
                        System.out.println("[ " + (char) temp + " value: " + temp + " | " + entry.getValue() + "\t]");
                });
        }
}