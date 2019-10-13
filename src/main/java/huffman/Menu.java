package huffman;

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

        protected void menu() {
                Scanner in = new Scanner(System.in);
                String path;

                if (this.args != null) {
                        for (String option : this.args) {
                                if (option.equals("c")) {
                                        if (this.args.length == 1) {
                                                System.out.print("File path: ");
                                                path = in.nextLine();
                                                System.out.println("Compressing " + path + "\n");
                                                Compress toupeira = new Compress(path);
                                                System.out.println("Hello world!");
                                                BSTree test = new BSTree(toupeira.getMap());
                                                test.print();
                                        } else {
                                                System.out.println("Compressing " + this.args[1]
                                                                + "\n");
                                                Compress toupeira = new Compress(this.args[1]);
                                                System.out.println("Hello world!");
                                                BSTree test = new BSTree(toupeira.getMap());
                                                test.print();
                                        }

                                } else if (option.equals("d")) {
                                        System.out.println("Decompressing \n");

                                        // TODO decompress
                                }
                        }
                }

                in.close();
        }

        protected void printHelp() {
                System.out.println("No Arguments provided");
                System.out.println("Use: java -jar huffman.jar [option] [file]");
                System.out.println("\tc - To compress file.");
                System.out.println("\td - To decompress file.");
                System.out.println("\th - To print this help.\n");
        }
}
