package tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.SpecBuilder;
import base.TestBase;
import base.TestDataBuild;
import constants.Authorization;
import constants.Content_Types;
import constants.EndPoints;
import constants.StatusCode;
import constants.StatusLine;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ReadConfig;
import utils.RestUtils;

public class BookingTestEndToEndTest extends TestBase{
	TestDataBuild data = new TestDataBuild();
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	static String TokenID;
	static int BookingID;
	// 1. POST create Token
	@Test(priority=1)
	public void userShouldBeAbleToCreateToken() throws IOException
	{
	String response =
					given(SpecBuilder.getRequestSpec())
						.body(data.createTokenPayload())
					.when()
						.post(EndPoints.AUTH_BOOKING)
					.then()
						.spec(SpecBuilder.getResponseSpec())
						.assertThat()
						.statusCode(StatusCode.CODE_200.code)
						.statusLine(StatusLine.STATUS_LINE_OK)
						.header("Content-Type",Content_Types.APPLICATION_JSON)
						.header("Content-Length",Matchers.equalTo("27"))
						.body("token", Matchers.notNullValue())
						.body("token.length()", Matchers.is(15))
						.body("token", Matchers.matchesRegex("^[a-z0-9]+$"))
						.extract()
						.response()
						.asString();
	JsonPath responseBody = new JsonPath(response);
	TokenID = responseBody.get("token");
	System.out.println("The TokenID is :"+responseBody.get("token"));
	}
	// 2. POST CREATE BOOKING
	@Test(priority=2)
	public void userShouldBeAbleToCreateBooking() throws IOException
	{
		String response =
				given(SpecBuilder.getRequestSpec())
					.body(data.createBookingPayload())
				.when()
					.post(EndPoints.POST_CREATE_BOOKING)
				.then()
					.spec(SpecBuilder.getResponseSpec())
					.assertThat()
					.statusCode(StatusCode.CODE_200.code)
					.statusLine(StatusLine.STATUS_LINE_OK)
					.header("Content-Type",Content_Types.APPLICATION_JSON)
					.header("Content-Length",Matchers.lessThan("300"))
					.body("booking.firstname", Matchers.equalTo(data.createBookingPayload().getFirstname()))
	        		.body("booking.lastname", Matchers.equalTo(data.createBookingPayload().getLastname()))
	        		.body("booking.totalprice", Matchers.equalTo(data.createBookingPayload().getTotalprice()))
	        		.body("booking.depositpaid", Matchers.equalTo(data.createBookingPayload().getDepositpaid()))
	        		.body("booking.bookingdates.checkin", Matchers.equalTo(data.createBookingPayload().getBookingdates().getCheckin()))
	        		.body("booking.bookingdates.checkout", Matchers.equalTo(data.createBookingPayload().getBookingdates().getCheckout()))
	        		.body("booking.additionalneeds", Matchers.equalTo(data.createBookingPayload().getAdditionalneeds()))
					.extract()
					.response()
					.asString();
		JsonPath responsePostBody = new JsonPath(response);
		Assert.assertTrue(responsePostBody!=null);
		Assert.assertTrue(responsePostBody.get("bookingid")!=null);
		BookingID = responsePostBody.get("bookingid");
		System.out.println("The BookingID is :"+responsePostBody.get("bookingid"));
	}
	// 3. GET BOOKING
	@Test(priority = 3)
	public void userShouldBeAbleTogetBooking() throws IOException

	{
				given(SpecBuilder.getRequestSpec())
					.pathParam("id", BookingID)
				.when()
					.get(EndPoints.GET_SINGLE_BOOKING)
				.then()
					.spec(SpecBuilder.getResponseSpec())
					.assertThat()
					.statusCode(StatusCode.CODE_200.code)
					.statusLine(StatusLine.STATUS_LINE_OK)
					.header("Content-Type",Content_Types.APPLICATION_JSON)
					.header("Content-Length",Matchers.lessThan("300"))
	        		.body("firstname", Matchers.equalTo(data.createBookingPayload().getFirstname()))
	        		.body("lastname", Matchers.equalTo(data.createBookingPayload().getLastname()))
	        		.body("totalprice", Matchers.equalTo(data.createBookingPayload().getTotalprice()))
	        		.body("depositpaid", Matchers.equalTo(data.createBookingPayload().getDepositpaid()))
	        		.body("bookingdates.checkin", Matchers.equalTo(data.createBookingPayload().getBookingdates().getCheckin()))
	        		.body("bookingdates.checkout", Matchers.equalTo(data.createBookingPayload().getBookingdates().getCheckout()))
	        		.body("additionalneeds", Matchers.equalTo(data.createBookingPayload().getAdditionalneeds()))
					.extract()
					.response();
	}
	// 4 UPDATE BOOKING
	@Test(priority=4)
	public void userShouldBeAbleToUpdateBooking() throws IOException
	{
				given(SpecBuilder.getRequestSpec())
						.auth()
						.preemptive()
						.basic(Authorization.USERNAME,Authorization.PASSWORD)
						.body(data.upadateBookingPayload())
						.pathParam("id", BookingID)
				.when()
						.put(EndPoints.PUT_UPDATE_BOOKING)
				.then()
						.spec(SpecBuilder.getResponseSpec())
						.assertThat()
						.statusCode(StatusCode.CODE_200.code)
						.statusLine(StatusLine.STATUS_LINE_OK)
						.header("Content-Type",Content_Types.APPLICATION_JSON)
						.header("Content-Length",Matchers.lessThan("300"))
						.body("firstname", Matchers.equalTo(data.upadateBookingPayload().getFirstname()))
						.body("lastname", Matchers.equalTo(data.upadateBookingPayload().getLastname()))
						.body("totalprice", Matchers.equalTo(data.upadateBookingPayload().getTotalprice()))
						.body("depositpaid", Matchers.equalTo(data.upadateBookingPayload().getDepositpaid()))
						.body("bookingdates.checkin", Matchers.equalTo(data.upadateBookingPayload().getBookingdates().getCheckin()))
						.body("bookingdates.checkout", Matchers.equalTo(data.upadateBookingPayload().getBookingdates().getCheckout()))
						.body("additionalneeds", Matchers.equalTo(data.upadateBookingPayload().getAdditionalneeds()))
						.extract()
						.response();

	}
   // 5. UPDATE PARTIAL BOOKING
	@Test(priority=5)
	public void userShoudBeAbleToUpdatePartialBooking() throws IOException

	{
				given(SpecBuilder.getRequestSpec())
						.auth()
						.preemptive()
						.basic(Authorization.USERNAME,Authorization.PASSWORD)
						.body(data.updatePartialBookingPayload())
						.pathParam("id", BookingID)
				.when()
						.patch(EndPoints.PATCH_UPDATE_BOOKING)
				.then()
						.spec(SpecBuilder.getResponseSpec())
						.assertThat()
						.statusCode(StatusCode.CODE_200.code)
						.statusLine(StatusLine.STATUS_LINE_OK)
						.header("Content-Type",Content_Types.APPLICATION_JSON)
						.header("Content-Length",Matchers.lessThan("300"))
						.body("firstname", Matchers.equalTo(data.updatePartialBookingPayload().getFirstname()))
						.body("lastname", Matchers.equalTo(data.updatePartialBookingPayload().getLastname()))
						.extract()
						.response();

	}
	// 6. DELETING BOOKING
	@Test(priority=6)
	public void userShouldBeAbleToDeleteBooking() throws IOException

	{
	Response response =
				given(SpecBuilder.getRequestSpec())
						.auth()
						.preemptive()
						.basic(Authorization.USERNAME,Authorization.PASSWORD)
						.pathParam("id", BookingID)
				.when()
						.delete(EndPoints.DELETE_BOOKING)
				.then()
						.spec(SpecBuilder.getResponseSpec())
						.assertThat()
						.statusCode(StatusCode.CODE_201.code)
						.statusLine(StatusLine.STATUS_LINE_CREATED)
						.header("Content-Type",Content_Types.APPLICATION_TEXT_PLAIN)
						.header("Content-Length",Matchers.equalTo("7"))
						.extract()
						.response();
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody!=null);
		Assert.assertEquals(responseBody.contains("Created"),true);

	}
}
