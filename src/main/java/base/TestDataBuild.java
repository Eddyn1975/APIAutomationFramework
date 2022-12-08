package base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pojo.Booking;
import pojo.Bookingdates;
import pojo.Root;
import pojo.Token;
import utils.ExcelReader;
import utils.RestUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDataBuild {
	public Booking booking = new Booking();
	Token token = new Token();
	Root root = new Root();
	ExcelReader data = new ExcelReader(".\\data\\BookingData.xlsx");
	String userName = data.getCellData("CreatingToken", "userName", 2);
	String Password = data.getCellData("CreatingToken", "Password", 2);

	public Token createTokenPayload() {
		token.setUsername(userName);
		token.setPassword(Password);
		return token;
	}

	public Booking createBookingPayload() {

		booking.setFirstname("Steve");
		booking.setLastname("McGraw");
		booking.setTotalprice(150);
		booking.setDepositpaid(true);
		Bookingdates bookingdate = new Bookingdates();
		bookingdate.setCheckin("2022-01-31");
		bookingdate.setCheckout("2022-01-31");
		booking.setBookingdates(bookingdate);
		booking.setAdditionalneeds("Breakfast, Lunch and Diner");
		return booking;

	}

	public Booking upadateBookingPayload() {
		booking.setFirstname("Sylvester");
		booking.setLastname("Stallone");
		booking.setTotalprice(190);
		booking.setDepositpaid(false);
		Bookingdates bookingdate = new Bookingdates();
		bookingdate.setCheckin("2022-02-28");
		bookingdate.setCheckout("2022-03-31");
		booking.setBookingdates(bookingdate);
		booking.setAdditionalneeds("Breakfast and Diner");
		return booking;
	}

	public Booking updatePartialBookingPayload() {
		booking.setFirstname("Sharone");
		booking.setLastname("Stone");
		return booking;
	}

}
