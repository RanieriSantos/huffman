package huffman;

import java.io.IOException;

public class Main {
        public static void main(String[] args) throws IOException {
                Compress toupeira = new Compress("docs/testes/teste3.txt");
                Decompress detoupeira = new Decompress("docs/compressed.edz", "docs/symbol_table.edt");

                // Menu access = new Menu(args);
                // access.menu();
                System.out.println("THE END?");
        }
}
// TODO hier
/*
 * Remember to remove garbage code; Handle exceptions.
 */