import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExamIO {

    public static void copyLargeTxtFiles(String from, String to, long size) throws IOException {
        File f = new File(from);
        File t = new File(to);

        if(f.exists()){
            File[] files = f.listFiles();
            if(!t.exists())
                t.createNewFile();
            for(File file:files){
                if(file.isFile()){
                    if(file.getName().endsWith(".txt") && file.length() > size){

                        file.renameTo(new File(t.getName() + "\\" + file.getName()));
                    }
                }
            }

        }else {
            System.out.println("Ne postoi");
        }
    }


    public static void serializeData(String destination, List<byte[]> data) throws IOException {
        OutputStream output = null;
        try{
            output = new FileOutputStream(destination);
            for(byte []bytes:data){
                for(byte b:bytes){
                    output.write(b);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            output.close();
        }
    }

    public static byte[] deserializeDataAtPosition(String source, long position, long elementLength) throws IOException {
        byte []bytes = new byte[(int)elementLength];
        RandomAccessFile rf = null;

        try {
            rf = new RandomAccessFile(source, "rw");
            long where = position*elementLength - elementLength;
            int k = 0;
            int c;
            for(long i=where;i<where+elementLength;i++){
                rf.seek(i);
                c = rf.read();
                bytes[k++] = (byte)c;

            }
        }
        finally {
            rf.close();
        }

        return bytes;
    }

    public static void main(String []args) throws IOException {
        // copyLargeTxtFiles("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2","E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2\\move",59);
//        Random r = new Random();
//        List<byte[]> list = new ArrayList<>();
//
//        for(int i=0;i<100;i++){
//            byte[] a = new byte[10];
//            for(int j=0;j<10;j++) {
//                char c = (char) (r.nextInt(26) + 'a');
//                a[j] = (byte) c;
//            }
//            list.add(a);
//        }
//
//        serializeData("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2\\write.txt",list);
//
//        byte[] test = deserializeDataAtPosition("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2\\deserialize.txt",2,5);
//        for (byte b:test){
//            System.out.print((char)b);
//        }
    }

}
