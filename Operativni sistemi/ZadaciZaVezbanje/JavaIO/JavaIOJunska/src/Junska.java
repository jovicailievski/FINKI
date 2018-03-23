import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Junska {

    public static void append(File read) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        try{
           in = new FileInputStream(read);
           out = new FileOutputStream("resources\\writable-content.txt",true);

           int c;

           while((c=in.read()) != -1){
               out.write((char)c);
           }
        }
        finally {
            in.close();
            out.close();
        }
    }

    public static void manage(String in, String out) throws IOException {
        File source = new File(in);
        File destination = new File(out);

        if(!source.exists()) {
            System.out.println("ne postoi");
            return;
        }

        if(destination.isDirectory()){
            File dest[] = destination.listFiles();
            for(File file:dest){
                System.out.println("deleting " + file.getAbsolutePath());
                file.delete();
            }
        }else{
            destination.createNewFile();
        }

        File[] files = source.listFiles();

        for (File file : files) {
            if (file.getName().endsWith(".dat")) {
                if (file.isHidden()) {
                    System.out.println("zbunet sum " + file.getAbsolutePath());
                    file.delete();
                }
                else if (file.canWrite()) {
                    System.out.println("pomestuvam " + file.getAbsolutePath());
                    file.renameTo(new File(destination.getName()+"\\"+  file.getName()));
                }
                else if (!file.canWrite()) {
                    System.out.println("dopisuvam " + file.getAbsolutePath());
                    append(file);
                }

            }

        }

    }

    public static void main(String[] args) throws IOException {
        manage("E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\JavaIOJunska","E:\\FINKI-GIT\\Operativni sistemi\\ZadaciZaVezbanje\\JavaIO\\JavaIOJunska\\move");
    }
}
