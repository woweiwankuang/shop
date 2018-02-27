package com.example.soldrec.web;

import com.example.soldrec.domain.model.SoldRec;
import com.example.soldrec.domain.repository.SoldRecRepository;
import com.example.soldrec.domain.service.SoldRecService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 销售记录控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoldRecController {

    private final SoldRecService soldRecService;
    private final SoldRecRepository soldRecRepository;

    /**
     * 添加销售记录
     * @param soldRec 销售记录
     */
    @PostMapping("/soldRecs")
    public Integer addSoldRec(@RequestBody SoldRec soldRec) {
        return soldRecService.add(soldRec);
    }

    /**
     * 通过顾客姓名查询销售记录
     * @param customerIds 用户id
     */
    @GetMapping("/soldRecs")
    public List<SoldRec> querySoldRec(List<Integer> customerIds) {
        return soldRecService.queryAllSoldRecByCustomerIds(customerIds);
    }

    /**
     * 查询单个销售记录
     * @param id 销售记录id
     */
    @GetMapping("/soldRecs/{id}")
    public SoldRec querySoldRecById(@PathVariable Integer id){
        return soldRecRepository.findOne(id);
    }

    /**
     * 更新销售记录
     */
    @PutMapping("/soldRecs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSoldRec(@PathVariable Integer id,@RequestBody SoldRec soldRec){
        soldRecRepository.save(soldRec);
    }
}
