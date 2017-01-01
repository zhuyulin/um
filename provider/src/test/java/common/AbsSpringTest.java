package common;

import org.junit.Before;
import org.springframework.test.context.TestContextManager;

/**
 * Created by Yuleen on 2016/12/25.
 */
public abstract class AbsSpringTest {
    @Before
    public void setUpContext() throws Exception {
        TestContextManager testContextManager;
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}
