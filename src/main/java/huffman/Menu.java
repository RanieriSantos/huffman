package huffman;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
        protected String[] args;

        public Menu(String[] args) {
                if (args.length == 0) {
                        printHelp();
                } else {
                        this.args = args;
                }
        }

        protected void menu() throws IOException {
                Scanner in = new Scanner(System.in);

                if (this.args != null) {
                        for (String option : this.args) {
                                if (option.equals("compress")) {
                                        Compress compress = new Compress(this.args[1], this.args[2], this.args[3]);
                                        break;
                                } else if (option.equals("extract")) {
                                        System.out.println("Decompressing \n");
                                        Decompress decompress = new Decompress(this.args[1], this.args[2],
                                                        this.args[3]);
                                        break;
                                } else {
                                        printHelp();
                                        break;
                                }
                        }
                }
                in.close();
        }

        protected void printHelp() {
                System.out.println("Invalid argument.");
                System.out.println("Use: java -jar huffman.jar compress file.txt file.edz file.edt");
                System.out.println("Use: java -jar huffman.jar extract file.edz file.edt file.txt");
                System.out.println("\tcompress - To compress file.");
                System.out.println("\textract - To decompress file.");
                System.out.println("\thelp - To print this help.\n");
        }
}
