package com.adele.semisample.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.adele.semisample.common.FileConfig.REAL_PATH;

public class FileUtil {
    public static int  uploadFiles(List<MultipartFile> files, MultipartFileToSqlConverter converter) throws IOException {
        int result = 0;
        for (MultipartFile file : files) {
            if(file.getOriginalFilename() != null && !file.getOriginalFilename().isBlank()) {
                String fileName = file.getOriginalFilename();
                String fileRename = fileRename(fileName);
                String filePath = REAL_PATH + fileRename;
                file.transferTo(new File(filePath));
                result += converter.fromMultipartFile(fileName, fileRename, filePath);
            }
        }
        return result;
    }

    private static String fileRename(String originalFileName) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileRename = UUID.randomUUID() + ext;
        return fileRename;
    }
}
