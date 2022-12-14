package com.cucumber.frame.api.steps;

import com.cucumber.frame.api.user.ColorsData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;

import static com.cucumber.frame.api.endpoint.ApiEndPoints.ENDPOINT_FOR_GET_LIST_OF_YEARS;
import static com.cucumber.frame.api.endpoint.ApiEndPoints.URL;



import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class YearsSteps {
    List<ColorsData> colors;
    private String endpoint;
    @Given("the endpoint for get sorted years")
    public void setEndpointForListOfYears() {
        endpoint = ENDPOINT_FOR_GET_LIST_OF_YEARS;
    }


    @When("get request for sorted years has been sent")
    public void getListOfYears() {
        colors = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + endpoint)
                .then().log().all()
                .statusCode(200).contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("data", ColorsData.class);
    }


    @Then("the response must contain a list of years sorted in ascending order")
    public void the_response_must_contain_a_list_of_years_sorted_in_ascending_order() {
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
    }
}
