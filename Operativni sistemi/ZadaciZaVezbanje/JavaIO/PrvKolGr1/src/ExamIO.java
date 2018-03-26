import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExamIO {
    public static void moveWritableTxtFiles(String from, String to) throws IOException {
        File here = new File(from);
        File there = new File(to);
        if(here.exists()) {
            there.mkdir();

            File[] datoteki = here.listFiles();

            for (File datoteka : datoteki) {
                if (datoteka.isFile() && datoteka.getName().endsWith(".txt") && datoteka.canWrite()) {
                    System.out.println(datoteka.getName());
                    datoteka.renameTo(new File(there.getName() + "\\" + datoteka.getName()));
                }

            }

        }else{
            System.out.println("Ne postoi");
        }

    }

    public static void deserializeData(String source, List<byte[]> data, long elementLength) throws IOException {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(source,"r");
            rf.seek(0);
            int c;
            int where = 0;
            int count = 0;
            rf.seek(where);
            byte[] readElement = new byte[(int)elementLength];
            while((c = rf.read())!= -1){
                if(count<elementLength){
                    readElement[count] = (byte)c;
                }else{
                    count = 0;
                    data.add(readElement);
                    readElement = new byte[(int)elementLength];
                    readElement[count] = (byte)c;
                }
                count++;
                where++;
                rf.seek(where);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            rf.close();
        }
    }

    public static void invertLargeFile(String source, String destination) throws IOException {
        RandomAccessFile input = null;
        RandomAccessFile output = null;
        try{
            input = new RandomAccessFile(source, "r");
            output = new RandomAccessFile(destination,"rw");
            long start = 0;
            long end = input.length()-1;
            int c;

            while(end >= 0){
                input.seek(end);
                output.seek(start);
                c = input.read();
                output.write(c);
                start++;
                end--;
            }
        }
        finally {
            input.close();
            output.close();
        }

    }


    public static void main(String []args) throws IOException {
        //moveWritableTxtFiles("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr1", "E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr1\\MoveHere");
        List<byte[]> data = new LinkedList<>();
        deserializeData("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr1\\text.txt",data,6);
        System.out.println("After method call");
        for(byte []a:data){
            String s = new String(a);
                System.out.println(s);
        }

        invertLargeFile("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr1\\text.txt","E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr1\\text-reverse.txt");

    }

}
