package com.example.supplier.web;

import com.example.supplier.domain.model.Supplier;
import com.example.supplier.domain.repository.SupplierRepository;
import com.example.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 供应商控制层
 */
@RestController
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierRepository supplierRepository;

    /**
     * 添加供应商
     * @param supplier 供应商
     */
    @PostMapping("/suppliers")
    public Integer addSupplier(@RequestBody @Valid Supplier supplier, @AuthenticationPrincipal User user) {
        supplier.setUserId(user.getId());
        return supplierRepository.save(supplier).getId();
    }

    /**
     * 查询单个供应商
     * @param id 供应商id
     */
    @GetMapping("/suppliers/{id}")
    public Supplier querySupplier(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Supplier supplier = supplierRepository.findOne(id);
        Assert.isTrue(user.getId().equals(supplier.getUserId()), "没有权限");
        return supplier;
    }

    /**
     * 更新供应商信息
     * @param id 供应商id
     * @param supplier 供应商信息
     */
    @PutMapping("/suppliers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuppliers(@PathVariable Integer id, @RequestBody Supplier supplier, @AuthenticationPrincipal User user) {
        Supplier dbSupplier = supplierRepository.findOne(id);
        Assert.isTrue(user.getId().equals(dbSupplier.getUserId()), "没有权限");
        supplier.setId(id);
        supplierRepository.save(supplier);
    }

    /**
     * 通过姓名查询供应商
     * @param name 姓名
     */
    @GetMapping("/suppliers")
    public List<Supplier> querySuppliers(String name, @AuthenticationPrincipal User user) {
        return supplierRepository.findAllByUserIdAndNameContaining(user.getId(), name);
    }
}
