import java.util.Random;

public class TenThreads {
    private static class WorkerThread implements Runnable {
        int max = Integer.MIN_VALUE;
        int[] ourArray;

        public WorkerThread(int[] ourArray) {
            this.ourArray = ourArray;
        }

        // Find the maximum value in our particular piece of the array
        public void run() {
            for (int i = 0; i < ourArray.length; i++)
                max = Math.max(max, ourArray[i]);
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) {
        WorkerThread[] runnables = new WorkerThread[20];
        Thread [] threads = new Thread[20];
        int[][] bigMatrix = getBigHairyMatrix();
        int max = Integer.MIN_VALUE;

        // Give each thread a slice of the matrix to work with
        for (int i = 0; i < 20; i++) {
            runnables[i] = new WorkerThread(bigMatrix[i]);
            threads[i] = new Thread(runnables[i]);
            threads[i].start();
        }

        // Wait for each thread to finish
        try {
            for (int i = 0; i < 10; i++) {
                threads[i].join(); // why is this needed
                max = Math.max(max, runnables[i].getMax());
            }
        } catch (InterruptedException e) {
            // fall through
        }

        System.out.println("Maximum value was " + max);

    }

    static void printMatrix(int [][]matrix){
        for(int []r:matrix){
            for(int e:r){
                System.out.print(e +" ");
            }
            System.out.println();
        }
    }

    static int[][] getBigHairyMatrix() {
        int x = 100;
        int y = 100;

        int[][] matrix = new int[x][y];
        Random rnd = new Random();

        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                matrix[i][j] = rnd.nextInt();
            }

        return matrix;
    }

}