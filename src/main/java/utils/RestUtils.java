package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;

import com.github.javafaker.Faker;

public class RestUtils {
	static Properties prop;
	static LocalDate currentDate = LocalDate.now();
	static Faker faker = new Faker();
	static Random rand = new Random();

	// RequestSpecification reqspec;
	public static String FirstName() {
		String firstname = faker.name().firstName();
		return firstname;
	}

	public static String LastName() {
		String lastname = faker.name().lastName();
		return lastname;
	}

	public static int TotalPrice() {
		int totalprice = faker.number().numberBetween(100, 999);
		return totalprice;
	}

	public static String Checkin() {
		// booking1.setCheckin(currentDate.plusDays(10).toString());
		String checkin = (currentDate.plusDays(1).toString());
		return checkin;

	}

	public static String Checkout() {
		String Checkout = (currentDate.plusDays(8).toString());
		return Checkout;
	}

	public static String AdditionalNeeds() {
		String[] StringArray = { "Breaksfast", "Breakfast, Lunch and Diner", "Diner", "Lunch and Diner",
				"Breakfast and Diner", "Lunch", "Breakfast and Lunch" };
		int additionalneeds = rand.nextInt(StringArray.length);
		return (StringArray[additionalneeds]);
	}

	public static Boolean DepositedPaid() {
		boolean depositedPaid = (rand.nextBoolean());
		return depositedPaid;
	}

	public static String getGlobalValue(String key) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
		prop.load(fis);
		prop.getProperty(key);
		return prop.getProperty(key);
	}

}
