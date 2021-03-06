import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static void main(String[] args) {

        List<String> extList = new ArrayList<>();
        extList.add("csv");
        extList.add("json");

        Map<File, String> filesMap= new HashMap<>();

        for (String filename : args) {

            File file = new File(filename);
            String extension = getFileExtension(file);
            System.out.println(extension);

            try {
                if (file.exists() & extList.contains(extension.toLowerCase())) {
                    filesMap.put(file, extension);
                    //System.out.println(filename + " exists");
                } else  System.out.println("ERROR: " + filename + " NOT exists in source folder \n or has not supported extension");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List <NewThread> threadsList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
           threadsList.add(new NewThread(i));
        }

        for(NewThread e : threadsList) e.start();
    }

    //метод, извлекающий расширение файла
    private static String getFileExtension(File file) {

        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
