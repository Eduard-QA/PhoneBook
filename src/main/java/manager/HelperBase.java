package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(Keys.COMMAND+"a");
        element.sendKeys(Keys.BACK_SPACE);

        if(text!=null){

            element.sendKeys(text);
        }

    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }


    public void test(String data){ //"30"

        String locator="//div[text()='" + data+ "']";

        click(By.xpath(locator));

    }
}