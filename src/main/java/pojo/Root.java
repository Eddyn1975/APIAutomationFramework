package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {

	private Integer bookingid;

	private Booking booking;

	public Integer getBookingid() {
		return bookingid;
	}

	public Root setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
		return this;
	}

	public Booking getBooking() {
		return booking;
	}

	public Root setBooking(Booking booking) {
		this.booking = booking;
		return this;
	}

}