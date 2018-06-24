package com.example.soldrec.web;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.repository.CustomerRepository;
import com.example.soldrec.domain.model.SoldRec;
import com.example.soldrec.domain.repository.SoldRecRepository;
import com.example.soldrec.domain.service.SoldRecService;
import com.example.soldrec.web.dto.SoldRecDTO;
import com.example.soldrec.web.dto.SoldRecStatistics;
import com.example.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 销售记录控制层
 */
@RestController
@RequiredArgsConstructor
public class SoldRecController {

    private final SoldRecService soldRecService;
    private final SoldRecRepository soldRecRepository;
    private final CustomerRepository customerRepository;

    /**
     * 添加销售记录
     * @param soldRec 销售记录
     */
    @PostMapping("/soldRecs")
    public Integer addSoldRec(@RequestBody SoldRec soldRec, @AuthenticationPrincipal User user) {
        soldRec.setUserId(user.getId());
        return soldRecService.add(soldRec);
    }

    /**
     * 通过顾客姓名查询销售记录
     * @param customerIds 用户id
     */
    @GetMapping(value = "/soldRecs", params = "type=customer")
    public List<SoldRecDTO> querySoldRec(@RequestParam(required = false) List<Integer> customerIds, @AuthenticationPrincipal User user) {
        List<SoldRec> soldRecs = soldRecService.queryAllSoldRecByCustomerIds(customerIds, user.getId());
        return change2DTO(soldRecs);
    }

    /**
     * 通过时间段查询销售记录
     */
    @GetMapping(value = "/soldRecs", params = "type=time")
    public List<SoldRecDTO> querySoldRec(@RequestParam Long startTime, @RequestParam Long endTime, @AuthenticationPrincipal User user) {
        List<SoldRec> soldRecs = soldRecRepository.findAllByUserIdAndSoldTimeBetween(user.getId(), startTime, endTime, new Sort(Sort.Direction.DESC, "id"));
        return change2DTO(soldRecs);
    }

    //将list<soldRec> -> List<SoldRecDTO>
    private List<SoldRecDTO> change2DTO(List<SoldRec> soldRecs){
        Set<Integer> tmpCustomerIds = soldRecs.stream().map(SoldRec::getCustomerId).collect(Collectors.toSet());
        Map<Integer, Customer> customerMap = customerRepository.findAll(tmpCustomerIds).stream().collect(Collectors.toMap(Customer::getId, customer -> customer));
        return soldRecs.stream().map(soldRec -> new SoldRecDTO(soldRec, customerMap.get(soldRec.getCustomerId()))).collect(Collectors.toList());
    }

    /**
     * 查询单个销售记录
     * @param id 销售记录id
     */
    @GetMapping("/soldRecs/{id}")
    public SoldRecDTO querySoldRecById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        SoldRec soldRec = soldRecRepository.findOne(id);
        Assert.isTrue(soldRec != null, "不存在该记录");
        Assert.isTrue(user.getId().equals(soldRec.getUserId()), "没有权限");
        Customer customer = customerRepository.findOne(soldRec.getCustomerId());
        return new SoldRecDTO(soldRec, customer);
    }

    /**
     * 更新销售记录
     */
    @PutMapping("/soldRecs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSoldRec(@PathVariable Integer id, @RequestBody SoldRec soldRec, @AuthenticationPrincipal User user) {
        SoldRec dbSoldRec = soldRecRepository.findOne(id);
        Assert.isTrue(user.getId().equals(dbSoldRec.getUserId()), "没有权限");
        soldRec.setId(id);
        soldRecRepository.save(soldRec);
    }

    /**
     * 统计时间段内的销售情况
     * @param startTime 开始统计时间
     * @param endTime 结束统计时间
     */
    @PostMapping("/soldRecs/statistics")
    public SoldRecStatistics extractSoldRecStatistics(@RequestParam Long startTime, @RequestParam Long endTime, @AuthenticationPrincipal User user) {
        double totalPrice = soldRecRepository.sumPriceByUserIdAndSoldTimeBetween(user.getId(), startTime, endTime);
        double totalCost = soldRecRepository.sumCostByUserIdAndSoldTimeBetween(user.getId(), startTime, endTime);
        double totalPostage = soldRecRepository.sumPostageByUserIdAndSoldTimeBetween(user.getId(), startTime, endTime);
        return new SoldRecStatistics(totalPrice, totalCost, totalPostage);
    }
}
