package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Filter.filter;
import Filter.notFilter;
import wifi.wifi_scans;

class testNotFilter {
	@Test
	void notTime() throws IOException {
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		filter filter = new filter();
		wifi_scans filtered = filter.byTime(scans, "27/10/2017 16:13:51", "27/10/2017 16:18:49");
		wifi_scans not = notFilter.notTime(scans, "27/10/2017 16:13:51", "27/10/2017 16:18:49");
		assertTrue(not.size()+filtered.size()==scans.size());
	}
	
	

}
