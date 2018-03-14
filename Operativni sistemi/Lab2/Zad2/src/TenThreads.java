import java.lang.reflect.Array;
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
        int maxRow = Integer.MIN_VALUE;
        WorkerThread[] runnables = new WorkerThread[40];
        Thread [] threads = new Thread[40];
        int[][] bigMatrix = getBigHairyMatrix();
        int max = Integer.MIN_VALUE;

        // Give each thread a slice of the matrix to work with
        for (int i = 0; i < 40; i++) {
            int column = 0;
            if(i < 20) {
                runnables[i] = new WorkerThread(bigMatrix[i]);
                threads[i] = new Thread(runnables[i]);
            }else{
                runnables[i] = new WorkerThread(getMatrixColumn(bigMatrix, column));
                threads[i] = new Thread(runnables[i]);
                column++;
            }
            threads[i].start();
        }

        // Wait for each thread to finish
        try {
            for (int i = 0; i < 40; i++) {
                threads[i].join();
                if(i < 20) {
                    max = Math.max(max, runnables[i].getMax());
                }else{
                    maxRow = Math.max(max, runnables[i].getMax());
                }
            }
        } catch (InterruptedException e) {
            // fall through
        }

        System.out.println("Maximum value was " + max);
        System.out.println("Maximum value by cols was " + maxRow);

    }

    static void printMatrix(int [][]matrix){
        for(int []r:matrix){
            for(int e:r){
                System.out.print(e +" ");
            }
            System.out.println();
        }
    }

    static int []getMatrixColumn(int matrix[][],int n){
        int column[] = new int [100];
        int i = 0;
        for(int row[]:matrix){
            column[i++] = row[n];
        }
        return column;
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