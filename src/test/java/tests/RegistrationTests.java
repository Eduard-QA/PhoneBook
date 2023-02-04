package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }

    }

    @Test(groups = {"smoke", "task"})
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(100);
        String email = "foxi" + i + "@gmail.com";

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, "Nnoa12345$");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(1000);
        Assert.assertTrue(app.getHelperUser().isLogged());


    }

    @Test
    public void registrationWrongEmail() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("foxgmail.com", "Nnoa12345$");
        app.getHelperUser().submitRegistration();
//        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));


    }

    @Test
    public void registrationWrongPassword() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("fox@gmail.com", "Nn12$");
        app.getHelperUser().submitRegistration();
//        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));

    }

    @Test
    public void registrationUserAlreadyExists() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("noa@gmail.com", "Nnoa12345$");
        app.getHelperUser().submitRegistration();
//        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("User already exist"));

    }

}