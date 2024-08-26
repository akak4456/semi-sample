package com.adele.semisample.common;

@FunctionalInterface
public interface MultipartFileToSqlConverter {
    int fromMultipartFile(String fileName, String fileRename, String filePath);
}
