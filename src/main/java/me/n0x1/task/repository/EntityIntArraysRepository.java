package me.n0x1.task.repository;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.specification.Specification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EntityIntArraysRepository {
    private static final Logger logger = LogManager.getLogger();

    private static EntityIntArraysRepository instance;

    private final List<CustomEntityIntArray> arraysList;

    private EntityIntArraysRepository(){
        this.arraysList = new ArrayList<>();
    }

    public static EntityIntArraysRepository getInstance(){
        if(instance == null){
            instance = new EntityIntArraysRepository();
            logger.log(Level.INFO,"Warehouse created");
        }

        return instance;
    }

    public void add(CustomEntityIntArray entityIntArray) {
        arraysList.add(entityIntArray);
        logger.log(Level.INFO,"Added array with ID {} to repository", entityIntArray.getId());
    }

    public void remove(CustomEntityIntArray entityIntArray) {
        arraysList.remove(entityIntArray);
        logger.log(Level.INFO,"Removed array with ID {} to repository", entityIntArray.getId());
    }

    public Optional<CustomEntityIntArray> getById(long id){
        for (CustomEntityIntArray entityIntArray: arraysList){
            if (entityIntArray.getId() == id){
                return Optional.of(entityIntArray);
            }
        }
        logger.log(Level.ERROR,"Array with ID {} not found", id);
        return Optional.empty();
    }

    public List<CustomEntityIntArray> getAll() {
        return new ArrayList<>(arraysList);
    }

    public List<CustomEntityIntArray> query(Specification specification) {

        List<CustomEntityIntArray> results = new ArrayList<>();

        for(CustomEntityIntArray arr : arraysList){
            if(specification.specify(arr)){
                results.add(arr);
            }
        }

        logger.log(Level.INFO,"Query performed. Found {} arrays.", results.size());
        return results;
    }

    public List<CustomEntityIntArray> sort(Comparator<CustomEntityIntArray> comparator) {

        List<CustomEntityIntArray> sortedList = new ArrayList<>(arraysList);

        sortedList.sort(comparator);

        logger.log(Level.INFO,"Arrays sorted using comparator: {}", comparator.getClass().getSimpleName());
        return sortedList;
    }
}
