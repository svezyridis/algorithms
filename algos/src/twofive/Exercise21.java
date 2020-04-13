package twofive;

public class Exercise21 {
    public class Vector implements Comparable<Vector> {
        int[] array;

        public Vector(int dimension) {
            array = new int[dimension];
        }

        public void setVector(int[] array) {
            this.array = array;
        }

        @Override
        public int compareTo(Vector that) {
            for (int i = 0; i < array.length; i++) {
                if (this.array[i] > that.array[i])
                    return 1;
                else if (this.array[i] < that.array[i])
                    return -1;
            }
            return 0;
        }
    }
}
