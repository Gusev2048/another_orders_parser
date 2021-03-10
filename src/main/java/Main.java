import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
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

        List <Thread> readersList = new ArrayList<>();

        for(File i : filesMap.keySet()) {
            if(getFileExtension(i).equals("csv")) OrderCsvReaderFactory.csvOrderReader(i);
            //if(getFileExtension(i).equals("json")) readersList.add(new JsonReader(i));
        }

        for(Thread e : readersList) e.start();

    }

    //метод, извлекающий расширение файла
    private static String getFileExtension(File file) {

        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
