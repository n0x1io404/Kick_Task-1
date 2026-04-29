package me.n0x1.task.warehouse;

import me.n0x1.task.entity.CustomEntityIntArrayStatistics;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EntityIntArrayWarehouse {
    private static final Logger logger = LogManager.getLogger();

    private static EntityIntArrayWarehouse instance;

    private final Map<Long, CustomEntityIntArrayStatistics> map;

    private EntityIntArrayWarehouse() {
        this.map = new HashMap<>();
    }

    public static EntityIntArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new EntityIntArrayWarehouse();

            logger.log(Level.INFO,"ArrayWarehouse singleton created");
        }
        return instance;
    }

    public void put(Long arrayId, CustomEntityIntArrayStatistics statistics) {
        map.put(arrayId, statistics);
        logger.log(Level.INFO,"Statistics put for array ID {}: {}", arrayId, statistics);
    }

    public CustomEntityIntArrayStatistics get(Long arrayId) {
        return map.get(arrayId);
    }

    public void remove(Long arrayId) {
        map.remove(arrayId);
        logger.log(Level.INFO,"Statistics removed for array ID {}", arrayId);
    }

    public void update(Long arrayId, CustomEntityIntArrayStatistics statistics) {
        if (map.replace(arrayId, statistics) != null) {
            logger.log(Level.INFO,"Statistics updated for array ID {}: {}", arrayId, statistics);
        } else {
            logger.log(Level.ERROR,"Attempt to update non-existent statistics for array ID {}", arrayId);
        }
    }
}