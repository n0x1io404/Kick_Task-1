package me.n0x1.task.specification.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.specification.Specification;

public class IdSpecificationImpl implements Specification {
    private final int wantedId;

    public IdSpecificationImpl(int wantedId){
        this.wantedId = wantedId;
    }

    @Override
    public boolean specify(CustomEntityIntArray customArray) {
        return customArray.getId() == wantedId;
    }
}
