import org.joda.time.DateTime;
import org.junit.*;

import static org.junit.Assert.*;

public class TestCalendar {

	@Test
	public void dayOfWeekOFStartOfMonth(){
		for(int y = 1; y < 2016; y++){
			for(int m=1; m <= 12; m++ ){
				Calendar c = new Calendar(m, y);
				int dow = c.findStartDOW();
				DateTime dt = new DateTime(y, m, 1, 1, 0);
				assertEquals("Failure in "+ m+"/"+y, dt.getDayOfWeek()%7, dow);
				System.out.println("Success on "+ m+"/"+y);
				
			}
		}
	}
}
