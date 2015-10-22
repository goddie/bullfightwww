package com.xiaba2.core;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseNativeEntity extends BaseEntity<Long> {

    private Long id;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "native")
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
