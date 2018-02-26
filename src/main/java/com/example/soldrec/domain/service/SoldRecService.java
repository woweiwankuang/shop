package com.example.soldrec.domain.service;

import com.example.soldrec.domain.model.SoldRec;

import java.util.List;

/**
 * 销售记录service
 */
public interface SoldRecService {

    /**
     * 添加记录
     * @param soldRec 销售记录
     */
    Integer add(SoldRec soldRec);

    /**
     * 通过用户id查询所有销售记录
     * @param userIds 用户id
     */
    List<SoldRec> queryAllSoldRecByUserIds(List<Integer> userIds);
}
