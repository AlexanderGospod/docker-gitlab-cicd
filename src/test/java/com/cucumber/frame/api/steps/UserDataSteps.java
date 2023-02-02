package com.cucumber.frame.api.steps;

import com.cucumber.frame.api.pojos.User;
import com.cucumber.frame.api.model.UserUpdate;
import com.cucumber.frame.api.pojos.UserUpdateResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.time.*;
import java.util.List;

import static com.cucumber.frame.api.endpoint.ApiEndPoints.*;
import static io.restassured.RestAssured.given;

public class UserDataSteps {
    private List<User> users;
    UserUpdateResponse userUpdateResponse;
    private String endpoint;

    @Given("the endpoint for sending requests about a list of users")
    public void setupEndpointToGetListOfUsers() {
        endpoint = ENDPOINT_FOR_GET_LIST_OF_USERS;
    }

    @Given("the endpoint for update user information")
    public void theValidEndpointForUpdateUserInformation() {
        endpoint = ENDPOINT_FOR_UPDATE_USER_INFORMATION;
    }

    @When("get request has been sent")
    public void sentRequestToGetListOfUsers() {
        users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + endpoint)
                .then().log().all()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("data", User.class);
    }

    @Then("the response must contain an avatar containing an ID for each user")
    public void checkThatFnAvatarContainingIDFoEachUser() {
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Then("the response must contain an email ending with {string} for each user")
    public void checkThatEmailEndingWithRequiredEndForEachUser(String requiredEndOfTheEmail) {
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(requiredEndOfTheEmail)));
    }

    @Then("the response must contain first name")
    public void checkThatEachUserHasFirstName() {
        Assert.assertFalse(users.stream().allMatch(x -> x.getFirst_name().isBlank()));
    }

    @Then("the response must contain last name")
    public void checkThatEachUserHasLastName() {
        Assert.assertFalse(users.stream().allMatch(x -> x.getLast_name().isBlank()));
    }

    @When("update request has been sent")
    public void sentUpdateUserInformationRequest() {
        UserUpdate userUpdate = new UserUpdate("morpheus", "zion resident");
        userUpdateResponse = given()
                .body(userUpdate)
                .when()
                .contentType(ContentType.JSON)
                .put(URL + endpoint)
                .then().log().all()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().as(UserUpdateResponse.class);
    }

    @Then("the response must contain user update time")
    public void checkThatResponseContainUserUpdateTime() {
        ZoneId desiredTimeZone = ZoneId.of("Etc/GMT+0");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(desiredTimeZone);
        LocalDateTime currentTime = zonedDateTime.toLocalDateTime();
        Assert.assertTrue(Duration.between(currentTime, userUpdateResponse.getUpdatedAt()).getSeconds() <= 1);
    }
}
