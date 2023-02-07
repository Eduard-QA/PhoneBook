package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;
    HelperUser helperUser;
    HelperContact helperContact;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME.browserName())){ // chrome == firefox
            wd = new ChromeDriver();
            logger.info("All tests start in Chrome Browser");
        }else if(browser.equals(Browser.FIREFOX.browserName())){ // firefox === firefox
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            wd = new FirefoxDriver(options);
            logger.info("All tests star in FireFox Browser");
        } else if (browser.equals(Browser.IE.browserName())) {  //internet explorer
            wd = new InternetExplorerDriver();
        } else if (browser.equals(Browser.EDGE.browserName())) { // MicrosoftEdge
            wd = new EdgeDriver();
        }
//"C:\\Program Files\\Mozilla Firefox\\firefox.exe"
        WebDriverListener listener=new ListenerWD();
        wd=new EventFiringDecorator<>(listener).decorate(wd);


        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://telranedu.web.app");
        logger.info("Current Url -->" +wd.getCurrentUrl());
        helperUser= new HelperUser(wd);
        helperContact= new HelperContact(wd);

    }

    public void stop() {
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact helperContact() {
        return helperContact;
    }
}
