package com.adele.semisample.sample.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleFile {
    private Long fileNo;
    private String filePath;
    private String fileName;
    private String fileRename;
    private Long sampleNo;
}
