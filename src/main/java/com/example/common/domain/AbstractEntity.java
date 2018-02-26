package com.example.common.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 抽象实体
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    protected Integer id;
    @CreatedDate
    protected Long createTime;
    @LastModifiedDate
    protected Long lastModified;

    public AbstractEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public Long getLastModified() {
        return this.lastModified;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }
}
