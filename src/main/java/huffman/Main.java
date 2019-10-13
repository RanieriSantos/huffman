package huffman;

public class Main {
        public static void main(String[] args) {
                Compress toupeira = new Compress("docs/lolla.txt");

                BSTree test = new BSTree(toupeira.getMap());
                // test.print();

                PriorityQueue heap = new PriorityQueue(5);

                while (test.getRoot() != null) {
                        heap.add(test.getRoot());
                        test.delete(test.getRoot());
                }

                heap.print();

                // Menu access = new Menu(args);
                // access.menu();
        }
}
