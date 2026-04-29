package me.n0x1.task.comparator;

import me.n0x1.task.entity.CustomEntityIntArray;

import java.util.Comparator;

public class IdComparator implements Comparator<CustomEntityIntArray> {

    @Override
    public int compare(CustomEntityIntArray o1, CustomEntityIntArray o2){
        return Long.compare(o1.getId(), o2.getId());
    }
}