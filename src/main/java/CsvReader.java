import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CsvReader extends Thread {

    File file;
    CsvReader(File file){

        this.file = file;
    }

    public void run() {
        Path path = Paths.get(file.getAbsolutePath());
        String name = file.getName();
        byte[] content = null;

        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        MultipartFile result = new MockMultipartFile(name, content);

        uploadCSVFile(result);
    }

    public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Order> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Order.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Order> orders = csvToBean.parse();

                // TODO: save users in DB?

                // save users list on model
            } catch (Exception ex) {

            }
        }
}



