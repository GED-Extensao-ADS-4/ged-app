package br.apae.ged.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartFileConverter {

    private MultipartFileConverter () {
        throw new IllegalStateException("Utility class");
    }

    public static File convertToFile(MultipartFile multipartFile, String name) throws IOException {
        File convFile = new File("imgs", name);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
