package huffman;

public class Main {
        public static void main(String[] args) {
                Compress toupeira = new Compress("docs/lolla.txt");
                System.out.println("Hello world!");
                BSTree test = new BSTree(toupeira.getMap());
                test.print();
                // Menu access = new Menu(args);
                // access.menu();
        }
}
