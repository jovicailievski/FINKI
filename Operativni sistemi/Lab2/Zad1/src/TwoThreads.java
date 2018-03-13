public class TwoThreads {
    public static class Thread1 extends Thread {
        public void run() {
            System.out.println("A");
            System.out.println("B");
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            System.out.println("1");
            System.out.println("2");
        }
    }


    static class ThreadAB implements Runnable{
        private String []chars;
        public ThreadAB(String chars) {
            this.chars = chars.split("\\s+");
        }

        private void printChars(){
            for(String a: chars){
                System.out.println(a);
            }
        }


        @Override
        public void run() {
            this.printChars();
        }
    }

    public static void main(String[] args){
        ThreadAB r1 = new ThreadAB("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ");
        ThreadAB r2 = new ThreadAB("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26");

        Thread a = new Thread(r1);
        Thread b = new Thread(r2);
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.start();
    }

}
