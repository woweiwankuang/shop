package com.example.soldrec.web;

import com.example.soldrec.domain.model.SoldRec;
import com.example.soldrec.domain.service.SoldRecService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 销售记录控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoldRecController {

    private final SoldRecService soldRecService;

    /**
     * 添加销售记录
     * @param soldRec 销售记录
     */
    @PostMapping("/soldRecs")
    public Integer addSoldRec(@RequestBody SoldRec soldRec) {
        return soldRecService.add(soldRec);
    }

    /**
     * 通过买家姓名查询销售记录
     * @param userIds 用户id
     */
    @GetMapping("/soldRecs")
    public List<SoldRec> querySoldRec(List<Integer> userIds) {
        return soldRecService.queryAllSoldRecByUserIds(userIds);
    }
}
