package com.adele.semisample.sample.service.impl;

import com.adele.semisample.common.Page;
import com.adele.semisample.common.FileUtil;
import com.adele.semisample.sample.domain.Sample;
import com.adele.semisample.sample.domain.SampleFile;
import com.adele.semisample.sample.dto.SampleSearchCondition;
import com.adele.semisample.sample.dto.SampleWriteDTO;
import com.adele.semisample.sample.repository.SampleMapper;
import com.adele.semisample.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {
    private final SampleMapper sampleMapper;
    @Override
    public List<Sample> selectAll() {
        return sampleMapper.selectAll();
    }

    @Override
    public Page<Sample, Void> selectPage(int currentPage) {
        return Page.of(currentPage, sampleMapper.selectPageAllCount(), sampleMapper::selectPageAll);
    }

    @Override
    public Page<Sample, SampleSearchCondition> selectPageSearch(int currentPage, SampleSearchCondition searchCondition) {
        return Page.of(currentPage, sampleMapper.selectPageConditionCount(searchCondition), searchCondition, sampleMapper::selectPageCondition);
    }

    @Override
    public int insertSample(SampleWriteDTO dto, List<MultipartFile> uploadFiles) throws IOException {
        FileUtil.uploadFiles(uploadFiles, ((fileName, fileRename, filePath) -> {
            SampleFile sampleFile = new SampleFile();
            sampleFile.setFileName(fileName);
            sampleFile.setFileRename(fileRename);
            sampleFile.setFilePath(filePath);
            sampleFile.setSampleNo(1L);
            return sampleMapper.insertSampleFile(sampleFile);
        }));
        return 0;
    }
}
