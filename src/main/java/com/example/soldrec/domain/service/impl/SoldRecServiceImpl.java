package com.example.soldrec.domain.service.impl;

import com.example.soldrec.domain.model.SoldRec;
import com.example.soldrec.domain.repository.SoldRecRepository;
import com.example.soldrec.domain.service.SoldRecService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 销售记录service
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoldRecServiceImpl implements SoldRecService {

    private final SoldRecRepository soldRecRepository;


    @Override
    public Integer add(SoldRec soldRec) {
        return soldRecRepository.save(soldRec).getId();
    }

    @Override
    public List<SoldRec> queryAllSoldRecByCustomerIds(List<Integer> customerIds, int userId) {
        if (CollectionUtils.isEmpty(customerIds)) {
            return soldRecRepository.findAllByUserId(userId, new Sort(Sort.Direction.DESC, "id"));
        }
        return soldRecRepository.findAllByUserIdAndCustomerIdIn(userId, customerIds, new Sort(Sort.Direction.DESC, "id"));
    }
}
