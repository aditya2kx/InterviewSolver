import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by adi2ky on 3/19/17.
 */
public class TwoSumTest {
    @DataProvider
    public Object[][] bruteForceSuccessDataProvider() {

        return new Object[][] {
                {new int[] {2, 7, 11, 15}, 9, new int[] {0, 1}},
                {new int[] {6, 4, 2, 10}, 14, new int[] {1, 3}}
        };
    }

    @Test(dataProvider = "bruteForceSuccessDataProvider")
    public void bruteForceSuccess(final int[] input,
                                  final int target,
                                  final int[] expectedResult) {
        final int[] actualResult = TwoSum.bruteForce(input, target);
        assertEquals(actualResult, expectedResult);
    }

    @DataProvider
    public Object[][] bruteForceFailDataProvider() {

        return new Object[][] {
                {new int[] {2, 7, 11, 15}, 90}
        };
    }

    @Test(dataProvider = "bruteForceFailDataProvider",
          expectedExceptions = IllegalArgumentException.class)
    public void bruteForceFail(final int[] input,
                               final int target) {
        TwoSum.bruteForce(input, target);
    }
}
