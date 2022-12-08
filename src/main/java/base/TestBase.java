package base;

import java.lang.reflect.Method;

//import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod;

public class TestBase {
	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("STARTING TEST: " + m.getName());
	}

}
