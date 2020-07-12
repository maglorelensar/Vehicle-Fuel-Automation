package arackayit.DosyaIslemleri;

import java.io.File;
import java.io.FileInputStream;


public class DosyaOkuma implements IDosyaOkuma{

    String directory = System.getProperty("user.dir");
    String absolutePath = null;

    public DosyaOkuma(String fileName){
        this.absolutePath = directory + File.separator + fileName;
    }
    
    @Override
    public String TumSatirlariGetir() {
        String str = "";
        try {
            File file = new File(absolutePath);
            byte[] data;
            try (FileInputStream fis = new FileInputStream(file)) {
                data = new byte[(int) file.length()];
                fis.read(data);
            }

            str = new String(data, "UTF-8");
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return str;
    }
}
