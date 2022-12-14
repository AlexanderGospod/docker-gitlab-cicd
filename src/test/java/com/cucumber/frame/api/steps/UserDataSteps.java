package com.cucumber.frame.api.steps;

import com.cucumber.frame.api.user.UserData;
import com.cucumber.frame.api.user.UserUpdateTime;
import com.cucumber.frame.api.user.UserUpdateTimeResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.time.Clock;
import java.util.List;

import static com.cucumber.frame.api.endpoint.ApiEndPoints.*;
import static io.restassured.RestAssured.given;

public class UserDataSteps {
    private List<UserData> users;
    UserUpdateTimeResponse userTimeResponse;
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
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    @Then("the response must contain an avatar containing an ID for each user")
    public void checkThatFnAvatarContainingIDFoEachUser() {
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Then("the response must contain an email ending with {string} for each user")
    public void checkThatEmailEndingWithRequiredEndForEachUser(String requiredEndOfTheEmail) {
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith(requiredEndOfTheEmail)));
    }


    @When("update request has been sent")
    public void sentUpdateUserInformationRequest() {
        UserUpdateTime userUpdateTime = new UserUpdateTime("morpheus", "zion resident");
        userTimeResponse = given()
                .body(userUpdateTime)
                .when()
                .contentType(ContentType.JSON)
                .put(URL + endpoint)
                .then().log().all()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().as(UserUpdateTimeResponse.class);
    }

    @Then("the response must contain user update time")
    public void checkThatResponseContainUserUpdateTime() {
        String currentTime = Clock.systemUTC().instant().toString();
        Assert.assertTrue("the current time - " + currentTime + " differs from the time of updating data on the server - "
                        + userTimeResponse.getUpdatedAt().replaceAll("(.{7})$", "")
                , currentTime.contains(userTimeResponse.getUpdatedAt().replaceAll("(.{7})$", "")) );
    }
}
