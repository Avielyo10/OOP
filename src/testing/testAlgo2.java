package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import algos.algo_2DS;
import wifi.wifi_scans;

class testAlgo2 {

	@Test
	void testDS() throws IOException {
		boolean flag = true;
		wifi_scans noGps = algo_2DS.createDs("C:\\Users\\Aviel\\Downloads\\Ex2\\data\\TS1");
		System.out.println(noGps.get(0).getAlt());
		System.out.println(noGps.get(0).getLon());
		System.out.println(noGps.get(0).getLat());
		for (int i = 0; i < noGps.size(); i++) {
			if(!Objects.equals(noGps.get(i).getAlt(),"?")&&!Objects.equals(noGps.get(i).getLon(),"?")&&!Objects.equals(noGps.get(i).getLat(),"?")) {
				flag = false;
			}
		}
		assertTrue(flag);
	}
	@Test
	void test() {
		
	}
}
