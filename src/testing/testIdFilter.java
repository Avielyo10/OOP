package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Filter.idFilter;
import wifi.wifi_scan;

class testIdFilter {

	/**
	 * check for id result
	 */
	@Test
	void testId() {
		idFilter filter =new idFilter("Samsung");
		String s = "0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],27/10/2017 16:17,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI";
		String id ="LG";
		wifi_scan scan = new wifi_scan(id,s);
		assertFalse(filter.isId(scan));
	}
}
