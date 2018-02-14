package testing;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import wifi.wifi_scans;

class testWifiScans {

	/**
	 * check specific scan to see if it really is 9 networks
	 * @throws IOException
	 */
	@Test
	void testFinal() throws IOException {
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		int i = Integer.parseInt(scans.get(0).getNumOfNet());
		if(i==9) {
			System.out.println(true);
		}
	}
	/**
	 * see if we have some gsm mac addresses
	 * @throws IOException
	 */
	@Test
	void testFakeMac() throws IOException {
		boolean flag = false;
		wifi_scans scans = wifi_scans.createDs("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		wifi_scans.removeFakeMac(scans);
		for (int i = 0; i < scans.size(); i++) {
			if(!scans.get(i).getMac().contains(":")) {
				flag=true;
			}
		}
		assertFalse(flag);
	}
	/**
	 * no more than 10 network at the same scan
	 * @throws IOException
	 */
	@Test
	void moreThanTen() throws IOException {
		boolean flag = false;
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		for (int i = 0; i < scans.size(); i++) {
			if(Integer.parseInt(scans.get(i).getNumOfNet())>10) {
				flag = true;
			}
		}
		assertFalse(flag);
	}
	/**
	 * size of wifi scan can't be above 46
	 * @throws IOException
	 */
	@Test
	void sizeOfScan() throws IOException {
		boolean flag = false;
		wifi_scans scans = wifi_scans.all("C:\\Users\\Aviel\\eclipse-workspace\\OOP\\DATA");
		for (int i = 0; i < scans.size(); i++) {
			if((scans.get(i).getScan().size())>46) {
				flag = true;
			}
		}
		assertFalse(flag);
	}
}
