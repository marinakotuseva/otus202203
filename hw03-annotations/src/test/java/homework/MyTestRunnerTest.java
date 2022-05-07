package homework;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.NotThrownAssert;
import org.junit.jupiter.api.Test;

class MyTestRunnerTest {

    @Test
    void testRunnerWithAllSuccess() {
        NotThrownAssert notThrownAssert = Assertions.assertThatNoException();
        notThrownAssert.isThrownBy(() -> MyTestFramework.runTest("homework.MyClass"));
    }

    @Test
    void testRunnerWithFailedBefore() {
        NotThrownAssert notThrownAssert = Assertions.assertThatNoException();
        notThrownAssert.isThrownBy(() -> MyTestFramework.runTest("homework.FailedBeforeClass"));
    }

    @Test
    void testRunnerWithFailedAfter() {
        NotThrownAssert notThrownAssert = Assertions.assertThatNoException();
        notThrownAssert.isThrownBy(() -> MyTestFramework.runTest("homework.FailedAfterClass"));
    }

    @Test
    void testRunnerWithFailedTest() {
        Assertions.assertThatThrownBy(() -> MyTestFramework.runTest("homework.FailedTestClass"))
                .isInstanceOf(TestFailedException.class);
    }
}