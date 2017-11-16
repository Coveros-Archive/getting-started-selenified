package coveros.getting_started;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coveros.selenified.Locator;
import com.coveros.selenified.Selenified;
import com.coveros.selenified.application.App;

public class WhatsOpenIT extends Selenified{

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext test) {
        // set the base URL for the tests here
        setTestSite(this, test, "https://whatsopen.gmu.edu/");
        // set the author of the tests here
        setAuthor(this, test, "Matt Grasberger\n<br/>matthew.grasberger@coveros.com");
        // set the version of the tests or of the software, possibly with a
        // dynamic check
        setVersion(this, test, "0.0.1");
    }

    @DataProvider(name = "whats open locations", parallel = true)
    public Object[][] DataSetOptions() {
        return new Object[][] { new Object[] { "127","Chick-Fil-A" }, new Object[] { "1450", "Blaze Pizza" }, new Object[] { "199", "One Stop Patriot Shop" }, };
    }

    @Test(groups = { "sample" }, description = "A sample test to check the title")
    public void sampleTest() {
        // grab our main app object
        App app = this.apps.get();
        // perform some actions
        app.azzert().titleEquals("What's Open - George Mason University");
        // verify no issues
        finish();
    }
    
    @Test(dataProvider = "whats open locations", groups = { "sample" }, description = "A sample test to check a few different locations")
    public void sampleTestClickChickFilA(String buttonID, String locationName) {
        // grab our main app object
        App app = this.apps.get();
        // perform some actions
        app.newElement(Locator.ID, buttonID).click();
        app.newElement(Locator.ID, "info-name").assertEquals().text(locationName);
        // verify no issues
        finish();
    }

}
