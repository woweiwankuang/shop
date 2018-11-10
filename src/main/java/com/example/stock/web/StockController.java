package com.example.stock.web;

import com.example.stock.domain.model.Stock;
import com.example.stock.domain.repository.StockRepository;
import com.example.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 库存控制层
 */
@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;

    /**
     * 添加库存
     * @param stock 库存
     */
    @PostMapping("/stocks")
    public Integer addStock(@RequestBody @Valid Stock stock, @AuthenticationPrincipal User user) {
        stock.setUserId(user.getId());
        return stockRepository.save(stock).getId();
    }

    /**
     * 更新库存信息
     * @param id 库存id
     * @param stock 库存信息
     */
    @PutMapping("/stocks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStock(@PathVariable Integer id, @RequestBody Stock stock, @AuthenticationPrincipal User user) {
        Stock dbStock = stockRepository.findOne(id);
        Assert.isTrue(user.getId().equals(dbStock.getUserId()), "没有权限");
        stock.setId(id);
        stockRepository.save(stock);
    }

    /**
     * 查询单个库存
     * @param id 库存id
     */
    @GetMapping("/stocks/{id}")
    public Stock queryStockById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Stock stock = stockRepository.findOne(id);
        Assert.isTrue(stock != null, "不存在该库存");
        Assert.isTrue(user.getId().equals(stock.getUserId()), "没有权限");
        return stock;
    }

    /**
     * 统计时间段内的库存情况
     * @param startTime 开始统计时间
     * @param endTime 结束统计时间
     */
    @GetMapping(value = "/stocks", params = "type=time")
    public List<Stock> queryStocksByTime(@RequestParam Long startTime, @RequestParam Long endTime, @AuthenticationPrincipal User user) {
        return stockRepository.findAllByUserIdAndAndCreateTimeBetween(user.getId(), startTime, endTime);
    }

    /**
     * 根据名字模糊查询
     * @param name 名称
     */
    @GetMapping(value = "/stocks", params = "type=name")
    public List<Stock> queryStocksByName(String name,@AuthenticationPrincipal User user){
        if (StringUtils.isEmpty(name)){
            return stockRepository.findAllByUserId(user.getId());
        }
        return stockRepository.findAllByUserIdAndNameContaining(user.getId(), name);
    }


}
