package me.n0x1.task.observer.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.entity.CustomEntityIntArrayStatistics;
import me.n0x1.task.observer.EntityObserver;
import me.n0x1.task.service.impl.IntArrayActionImpl;
import me.n0x1.task.warehouse.EntityIntArrayWarehouse; // Добавил правильный импорт склада
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.OptionalDouble; // Добавил недостающий импорт
import java.util.OptionalInt;
import java.util.OptionalLong;

public class EntityObserverImpl implements EntityObserver {

    private static final Logger logger = LogManager.getLogger(EntityObserverImpl.class);

    private final IntArrayActionImpl service = new IntArrayActionImpl();

    @Override
    public void update(CustomEntityIntArray entityIntArray) {
        if (entityIntArray == null) {
            logger.log(Level.WARN,"Received null entityIntArray in observer update method");
            return;
        }

        long id = entityIntArray.getId();
        logger.log(Level.INFO,"Observer detected change in array ID: {}", id);

        int[] data = entityIntArray.getData();

        OptionalLong sumOpt = service.calculateSum(data);
        OptionalInt minOpt = service.findMin(data);
        OptionalInt maxOpt = service.findMax(data);
        OptionalDouble avgOpt = service.calculateAverage(data);

        int min = minOpt.orElse(Integer.MAX_VALUE);
        int max = maxOpt.orElse(Integer.MIN_VALUE);
        double average = avgOpt.orElse(0.0);
        long sum = sumOpt.orElse(Long.MAX_VALUE);

        CustomEntityIntArrayStatistics newStats = new CustomEntityIntArrayStatistics(min, max, sum, average);

        EntityIntArrayWarehouse warehouse = EntityIntArrayWarehouse.getInstance();

        warehouse.update(id, newStats);

        logger.info("Warehouse updated for array ID: {}", id);
    }

}