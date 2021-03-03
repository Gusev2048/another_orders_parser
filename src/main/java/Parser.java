import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static void main(String[] args) {

        Map<File, String> filesMap= new HashMap<>();

        for (String filename : args) {

            File file = new File(filename);

            try {
                if (file.exists()) filesMap.put(file, getFileExtension(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //метод, извлекающий расширение файла
    private static String getFileExtension(File file) {

        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
