package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(User.builder().email("noa@gmail.com").password("Nnoa12345$").build());
        }
    }
    @Test (groups = {"smoke"})
    public void addContactSuccessAllFields(){
        Random random = new Random();
        int i= random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lisa"+i)
                .lastName("Simpson")
                .address("NY")
                .phone("1111111"+i)
                .email("lisa"+i+"@mail.ru")
                .description("All fields").build();
        System.out.println(contact.toString());
        logger.info("Tests start with data : " +contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.helperContact().isContactAddedByEmail(contact.getEmail()));


    }
    @Test
    public void addContactSuccessRequiredFields(){
        Random random = new Random();
        int i= random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("WWWWWW"+i)
                .lastName("Simpson")
                .address("NY")
                .phone("1111111"+i)
                .email("marge"+i+"@mail.ru")
                .build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));

    }
    @Test
    public void  addNewContactWrongName(){

        Contact contact = Contact.builder()
                .name("")
                .lastName("Simpson")
                .address("NY")
                .phone("1111111333")
                .email("lisa@mail.ru")
                .description("wrong name").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }
    @Test
    public void  addNewContactWrongAddress(){

        Contact contact = Contact.builder()
                .name("Katty")
                .lastName("Simpson")
                .address("")
                .phone("1111111333")
                .email("katty@mail.ru")
                .description("wrong address").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }
    @Test
    public void  addNewContactWrongLastName(){

        Contact contact = Contact.builder()
                .name("John")
                .lastName("")
                .address("NY")
                .phone("1111111333")
                .email("john@mail.ru")
                .description("wrong Last name").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }
    @Test
    public void  addNewContactWrongPhone(){

        Contact contact = Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("")
                .email("john@mail.ru")
                .description("wrong Last name").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser()
                .isErrorMessageDisplayed("Phone not valid: Phone number must contain only digits! And length min 10, max 15"));

    }
    @Test
    public void  addNewContactWrongEmail(){

        Contact contact = Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("11111234567")
                .email("johnmail.ru")
                .description("wrong Last name").build();
        System.out.println(contact.toString());

        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().submitContactForm();
        //  Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser()
                .isErrorMessageDisplayed("Email not valid: must be a well-formed email address"));

    }

}