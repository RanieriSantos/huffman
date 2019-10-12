package huffman;

public class PriorityQueue {
        private Node[] data;
        private int size;

        PriorityQueue() {
                // TODO
        }

        protected void add() {
                // TODO
        }

        protected void poll() {
                // TODO
        }

        protected void remove() {
                // TODO
        }

        protected Node peek() {
                return this.data[0];
        }

        protected void decreaseKey() {
                // TODO
        }

        protected void clear() {
                // TODO
        }

        protected boolean empty() {
                return this.size == 0;
        }

        protected int getSize() {
                return size;
        }

        protected int getParentIndex(int k) {
                return (k - 1) / 2;
        }

        protected Node getParentNode(int k) {
                return this.data[getParentIndex(k)];
        }

        protected int getLeftIndex(int k) {
                return (2 * k) + 1;
        }

        protected Node getLeftNode(int k) {
                return this.data[getLeftIndex(k)];
        }

        protected int getRightIndex(int k) {
                return (2 * k) + 2;
        }

        protected Node getRightNode(int k) {
                return this.data[getRightIndex(k)];
        }
}
