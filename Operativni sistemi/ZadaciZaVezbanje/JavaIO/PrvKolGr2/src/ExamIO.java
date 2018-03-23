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

    public static void main(String []args) throws IOException {
        //copyLargeTxtFiles("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2","E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2\\move",59);
        Random r = new Random();
        List<byte[]> list = new ArrayList<>();

        for(int i=0;i<100;i++){
            byte[] a = new byte[10];
            for(int j=0;j<10;j++) {
                char c = (char) (r.nextInt(26) + 'a');
                a[j] = (byte) c;
            }
            list.add(a);
        }

        serializeData("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\PrvKolGr2\\write.txt",list);
   }

}
