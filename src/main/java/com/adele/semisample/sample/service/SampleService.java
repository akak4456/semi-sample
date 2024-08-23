package com.adele.semisample.sample.service;

import com.adele.semisample.common.Page;
import com.adele.semisample.sample.domain.Sample;
import com.adele.semisample.sample.dto.SampleSearchCondition;

import java.util.List;

public interface SampleService {
    List<Sample> selectAll();

    Page<Sample, Void> selectPage(int currentPage);

    Page<Sample, SampleSearchCondition> selectPageSearch(int currentPage, SampleSearchCondition searchCondition);
}
