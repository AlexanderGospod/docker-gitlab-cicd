package com.cucumber.frame.api.steps;

import com.cucumber.frame.api.model.Registration;
import com.cucumber.frame.api.pojos.registration.SuccessfulRegistration;
import com.cucumber.frame.api.pojos.registration.UnsuccessfulRegistration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import static com.cucumber.frame.api.endpoint.ApiEndPoints.ENDPOINT_FOR_USER_REGISTER;
import static com.cucumber.frame.api.endpoint.ApiEndPoints.URL;
import static io.restassured.RestAssured.given;

public class RegistrationSteps {
    SuccessfulRegistration successfulRegistration;
    UnsuccessfulRegistration unsuccessfulRegistration;

    private String endpoint;

    @Given("the endpoint for register user")
    public void setEndpointForRegister() {
        endpoint = ENDPOINT_FOR_USER_REGISTER;
    }

    @When("post request with email {string} and password {string} has been sent")
    public void sentPostRequestWithEmailAndPassword(String email, String password) {
        Registration user = new Registration(email, password);
        successfulRegistration = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(URL + endpoint)
                .then().log().all()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().as(SuccessfulRegistration.class);
    }

    @Then("the response must contain ID new user")
    public void checkThatResponseContainIDNewUser() {
        Integer id = 4;
        Assert.assertEquals(id, successfulRegistration.getId());
    }

    @Then("the response must contain token new user")
    public void checkThatResponseContainTokenNewUser() {
        String token = "QpwL5tke4Pnpja7X4";
        Assert.assertEquals(token, successfulRegistration.getToken());
    }


    @When("post request with email {string} and empty password has been sent")
    public void sentPostRequestWithEmailAndEmptyPassword(String email) {
        Registration user = new Registration(email, "");
        unsuccessfulRegistration = given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(URL + endpoint)
                .then().log().all()
                .statusCode(400).contentType(ContentType.JSON)
                .extract().as(UnsuccessfulRegistration.class);
    }

    @Then("the response must contain error: {string}")
    public void theResponseMustContainError(String expectedError) {
        Assert.assertEquals(expectedError, unsuccessfulRegistration.getError());
    }
}
