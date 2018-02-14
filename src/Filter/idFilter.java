package Filter;

import java.util.Objects;

import wifi.wifi_scan;

/**
 * filtering by id
 * @author Aviel
 *
 */
public class idFilter {
	String id;
	public idFilter(String id) {
		this.id = id;
	}
	
	/**
	 * @param scan
	 * @return if it's the right id
	 */
	public boolean isId(wifi_scan scan) {
		boolean flag=false;
		if(scan!=null) {
			if(Objects.equals(this.id, scan.getId())) {
				flag=true;
			}
		}
		return flag;
	}
}
