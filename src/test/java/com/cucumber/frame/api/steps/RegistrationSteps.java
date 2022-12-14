package com.cucumber.frame.api.steps;

import com.cucumber.frame.api.accesstosite.Registration;
import com.cucumber.frame.api.accesstosite.SuccessRegistration;
import com.cucumber.frame.api.accesstosite.UnsuccessRegistration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import static com.cucumber.frame.api.endpoint.ApiEndPoints.ENDPOINT_FOR_USER_REGISTER;
import static com.cucumber.frame.api.endpoint.ApiEndPoints.URL;
import static io.restassured.RestAssured.given;

public class RegistrationSteps {
    SuccessRegistration successRegistration;
    UnsuccessRegistration unsuccessRegistration;

    private String endpoint;
    @Given("the endpoint for register user")
    public void setEndpointForRegister() {
        endpoint = ENDPOINT_FOR_USER_REGISTER;
    }

    @When("post request with email {string} and password {string} has been sent")
    public void sentPostRequestWithEmailAndPassword(String email, String password) {
        Registration user = new Registration(email, password);
        successRegistration = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(URL + endpoint)
                .then().log().all()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().as(SuccessRegistration.class);
    }
    @Then("the response must contain ID new user")
    public void checkThatResponseContainIDNewUser() {
        Integer id = 4;
        Assert.assertEquals(id, successRegistration.getId());
    }

    @Then("the response must contain token new user")
    public void checkThatResponseContainTokenNewUser() {
        String token = "QpwL5tke4Pnpja7X4";
        Assert.assertEquals(token, successRegistration.getToken());
    }


    @When("post request with email {string} and empty password has been sent")
    public void sentPostRequestWithEmailAndEmptyPassword(String email) {
        Registration user = new Registration(email, "");
        unsuccessRegistration = given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(URL + endpoint)
                .then().log().all()
                .statusCode(400).contentType(ContentType.JSON)
                .extract().as(UnsuccessRegistration.class);
    }

    @Then("the response must contain error: Missing password")
    public void checkThatResponseContainErrorMessage() {
        Assert.assertEquals("Missing password", unsuccessRegistration.getError());
    }
}
