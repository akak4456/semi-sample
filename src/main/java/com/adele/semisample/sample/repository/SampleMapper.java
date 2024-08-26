package com.adele.semisample.sample.repository;

import com.adele.semisample.sample.domain.Sample;
import com.adele.semisample.sample.dto.SampleSearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SampleMapper {
    List<Sample> selectAll();

    List<Sample> selectPageAll(int currentPage, RowBounds rowBounds);

    List<Sample> selectPageCondition(int currentPage, SampleSearchCondition searchCondition, RowBounds rowBounds);

    int selectPageAllCount();

    int selectPageConditionCount(SampleSearchCondition searchCondition);
}
