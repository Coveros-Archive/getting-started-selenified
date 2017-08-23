package coveros.getting_started;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coveros.selenified.Locator;
import com.coveros.selenified.Selenified;
import com.coveros.selenified.application.App;

public class SampleTests extends Selenified {

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext test) {
        // set the base URL for the tests here
        setTestSite(this, test, "https://www.google.com");
        // set the author of the tests here
        setAuthor(this, test, "Matt Grasberger\n<br/>matthew.grasberger@coveros.com");
        // set the version of the tests or of the software, possibly with a
        // dynamic check
        setVersion(this, test, "0.0.1");
    }

    @DataProvider(name = "google search terms", parallel = true)
    public Object[][] DataSetOptions() {
        return new Object[][] { new Object[] { "python" }, new Object[] { "perl" }, new Object[] { "bash" }, };
    }

    @Test(groups = { "sample" }, description = "A sample test to check a title")
    public void sampleTest() throws IOException {
        // grab our main app object
        App app = this.apps.get();
        // perform some actions
        app.azzert().titleEquals("Google");
        // verify no issues
        finish();
    }

    @Test(dataProvider = "google search terms", groups = { "sample" },
            description = "A sample test using a data provider to perform searches")
    public void sampleTestWDataProvider(String searchTerm) throws Exception {
        // grab our main app object
        App app = this.apps.get();
        // perform some actions
        app.newElement(Locator.NAME, "q").type(searchTerm);
        app.newElement(Locator.NAME, "q").type(Keys.ENTER);
        app.newElement(Locator.ID, "resultStats").waitFor().displayed();
        app.azzert().titleEquals(searchTerm + " - Google Search");
        // verify no issues
        finish();
    }
}
