package test.n0x1.task.service.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.exception.CustomArrayException;
import me.n0x1.task.service.IntArrayAction;
import me.n0x1.task.service.impl.IntArrayActionImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IntArrayActionImplTest {

    private IntArrayAction actionService;

    @BeforeMethod
    public void setUp() {
        actionService = new IntArrayActionImpl();
    }

    @DataProvider(name = "provideDataForAction")
    public Object[][] provideDataForAction() {
        return new Object[][]{
                {new int[]{1, 2, 3, 4, 5}, 1, 5, 15, 3.0},
                {new int[]{-5, 0, 5, 10}, -5, 10, 10, 2.5},
                {new int[]{7, 7, 7}, 7, 7, 21, 7.0},
                {new int[]{-10, -20, -30}, -30, -10, -60, -20.0}
        };
    }

    @Test(dataProvider = "provideDataForAction")
    public void testMin(int[] input, int expectedMin, int expectedMax, int expectedSum, double expectedAverage) throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(input);

        int actual = actionService.min(entity).getAsInt();

        Assert.assertEquals(actual, expectedMin);
    }

    @Test(dataProvider = "provideDataForAction")
    public void testMax(int[] input, int expectedMin, int expectedMax, int expectedSum, double expectedAverage) throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(input);

        int actual = actionService.max(entity).getAsInt();

        Assert.assertEquals(actual, expectedMax);
    }

    @Test(dataProvider = "provideDataForAction")
    public void testSum(int[] input, int expectedMin, int expectedMax, int expectedSum, double expectedAverage) throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(input);

        int actual = actionService.sum(entity).getAsInt();

        Assert.assertEquals(actual, expectedSum);
    }

    @Test(dataProvider = "provideDataForAction")
    public void testAverage(int[] input, int expectedMin, int expectedMax, int expectedSum, double expectedAverage) throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(input);

        double actual = actionService.average(entity).getAsDouble();

        Assert.assertEquals(actual, expectedAverage, 0.0001);
    }
}