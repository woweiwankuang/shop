package com.example.soldrec.web;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.repository.CustomerRepository;
import com.example.soldrec.domain.model.SoldRec;
import com.example.soldrec.domain.repository.SoldRecRepository;
import com.example.soldrec.domain.service.SoldRecService;
import com.example.soldrec.web.dto.SoldRecDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 销售记录控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoldRecController {

    private final SoldRecService soldRecService;
    private final SoldRecRepository soldRecRepository;
    private final CustomerRepository customerRepository;

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
    public List<SoldRecDTO> querySoldRec(@RequestParam(required = false) List<Integer> customerIds) {
        List<SoldRec> soldRecs = soldRecService.queryAllSoldRecByCustomerIds(customerIds);
        List<Integer> tmpCustomerIds = soldRecs.stream().map(SoldRec::getCustomerId).collect(Collectors.toList());
        Map<Integer, Customer> customerMap = customerRepository.findAll(tmpCustomerIds).stream().collect(Collectors.toMap(Customer::getId, customer -> customer));
        return soldRecs.stream().map(soldRec -> new SoldRecDTO(soldRec, customerMap.get(soldRec.getCustomerId()))).collect(Collectors.toList());
    }

    /**
     * 查询单个销售记录
     * @param id 销售记录id
     */
    @GetMapping("/soldRecs/{id}")
    public SoldRecDTO querySoldRecById(@PathVariable Integer id) {
        SoldRec soldRec = soldRecRepository.findOne(id);
        Assert.isTrue(soldRec != null, "不存在该记录");
        Customer customer = customerRepository.findOne(soldRec.getCustomerId());
        return new SoldRecDTO(soldRec, customer);
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
