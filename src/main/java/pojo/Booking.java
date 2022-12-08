package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
	private String firstname;
	private String lastname;
	private Integer totalprice;
	private Boolean depositpaid;
	public Bookingdates bookingdates;
	private String additionalneeds;

	public String getFirstname() {
		return firstname;
	}

	public Booking setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public Booking setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public Integer getTotalprice() {
		return totalprice;
	}

	public Booking setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
		return this;
	}

	public Boolean getDepositpaid() {
		return depositpaid;
	}

	public Booking setDepositpaid(Boolean depositpaid) {
		this.depositpaid = depositpaid;
		return this;
	}

	public Bookingdates getBookingdates() {
		return bookingdates;
	}

	public Booking setBookingdates(Bookingdates bookingdates) {
		this.bookingdates = bookingdates;
		return this;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public Booking setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
		return this;
	}

}