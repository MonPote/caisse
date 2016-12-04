package Service.ServicesKit;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nea on 04/12/16.
 */

public class FileHandler {
    private String filename = "";
    private final String rootLocation = "src/upload-dir";

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //get current date time with Date()
        Date date = new Date();
        return dateFormat.format(date);
    }

    public FileHandler(MultipartFile file) {
        this.filename = getDate() + "-" + file.getOriginalFilename();
    }

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        if (filename.equals("")) {
            return "undifined";
        }
        return filename;
    }

    public void store(MultipartFile file, String targetAppli) throws IOException {
            // File baseDirectory = new File(rootLocation);
            // File subDirectory = new File(baseDirectory, targetAppli);
            File dir = new File(rootLocation + "/" + targetAppli);
            if (!dir.exists())
                dir.mkdirs();
            Path targetPath = new File(rootLocation + "/" + targetAppli + "/" + this.filename).toPath();
            Files.copy(file.getInputStream(), targetPath);
    }

    public Resource getFile(String targetAppli) {
        Path filePath = new File(rootLocation + "/" + targetAppli + "/" + this.filename).toPath();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
