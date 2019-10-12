package huffman;

public class Main {
        public static void main(String[] args) {
                Compress toupeira = new Compress("docs/testes/teste1.txt");
                System.out.println("Hello world!");
                BSTree test = new BSTree(toupeira.getMap());
                test.print();
        }
}
