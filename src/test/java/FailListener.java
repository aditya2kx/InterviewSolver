import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by adi2ky on 6/15/16.
 */
public class FailListener extends TestListenerAdapter {
    @Override
    public void onTestSkipped(ITestResult tr) {
        tr.setStatus(ITestResult.FAILURE);
    }
}
