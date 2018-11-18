package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

//    @When("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and matching password confirmation are entered$")
//    public void a_valid_username_and_a_valid_password_and_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
//        createUserWith(username, password, passwordConfirmation);
//    }

    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error) throws Throwable {
        pageHasContent(error);
        pageHasContent("Create username and give password");
    }

    @When("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_a_valid_password_and_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        createUserWith(username, password, passwordConfirmation);
    }

    @When("^a not valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are entered$")
    public void a_not_valid_username_and_a_valid_password_and_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        createUserWith(username, password, passwordConfirmation);
    }

    @When("^a valid username \"([^\"]*)\" and a not valid password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_a_not_valid_password_and_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        createUserWith(username, password, passwordConfirmation);
    }

    @When("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and not matching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_a_valid_password_and_not_matching_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        createUserWith(username, password, passwordConfirmation);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUserWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
