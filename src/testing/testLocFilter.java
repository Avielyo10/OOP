package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Filter.locFilter;
import wifi.wifi_scan;

class testLocFilter {

	/**
	 * see if the location is within range by formula found on the Internet
	 */
	@Test
	void test() {
		locFilter filter = new locFilter("35.567", "34.5657");
		String s = "0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],27/10/2017 16:17,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI";
		String id ="id";
		wifi_scan scan = new wifi_scan(id,s);
		assertTrue(filter.withinRange(scan, 360));
	}

}
