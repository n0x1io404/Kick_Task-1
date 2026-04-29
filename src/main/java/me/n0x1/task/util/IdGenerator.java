package me.n0x1.task.util;

public class IdGenerator {
    private static long currentId = 0;

    public static long generateId(){
        return ++currentId;
    }
}
