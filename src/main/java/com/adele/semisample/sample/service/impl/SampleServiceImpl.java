package com.adele.semisample.sample.service.impl;

import com.adele.semisample.common.Page;
import com.adele.semisample.sample.domain.Sample;
import com.adele.semisample.sample.dto.SampleSearchCondition;
import com.adele.semisample.sample.repository.SampleMapper;
import com.adele.semisample.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;

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
        return Page.of(currentPage, sampleMapper.selectPageAllCount(),sampleMapper::selectPageAll);
    }

    @Override
    public Page<Sample, SampleSearchCondition> selectPageSearch(int currentPage, SampleSearchCondition searchCondition) {
        return Page.of(currentPage, sampleMapper.selectPageConditionCount(searchCondition), searchCondition, sampleMapper::selectPageCondition);
    }
}
