package wifi;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * this class creates the simplest wifi scan
 * @author Aviel
 *
 */
public class wifi_scan {
	private ArrayList<String> scan;
	String mac;
	String ssid;
	String time;
	String lat;
	String lon;
	String alt;
	String signal;
	String id;
	String freq;
	String numOfNet;
	String score;
	public wifi_scan() {
		this.setScan(new ArrayList<String>());
	}
	public wifi_scan(wifi_scan _scan) {
		this.setScan(_scan.getScan());
		this.time = _scan.getScan().get(0);
		this.id = _scan.getScan().get(1);
		this.setLat(_scan.getScan().get(2));
		this.setLon(_scan.getScan().get(3));
		this.alt = _scan.getScan().get(4);
		this.setNumOfNet(_scan.getScan().get(5));
		this.ssid = _scan.getScan().get(6);
		this.mac = _scan.getScan().get(7);
		this.freq = _scan.getScan().get(8);
		this.signal = _scan.getScan().get(9);
		this.score = "0";
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public wifi_scan(String id,String data) {
		if (data!=null) {
			this.setScan(new ArrayList<String>(Arrays.asList(data.split(","))));
			this.mac = getScan().get(0);
			this.ssid = getScan().get(1);	
			this.time = getScan().get(3);
			this.setLat(getScan().get(6));
			this.setLon(getScan().get(7));
			this.alt = getScan().get(8);
			this.signal = getScan().get(5);
			getScan().add(id);
			this.id = id;
			Double signal=Double.valueOf(this.signal)*-1;
			Double freq = 300/signal;
			this.freq=String.valueOf((double)Math.round(freq * 100d) / 100d);
		}
	}
	@Override
	public String toString() {
		return getScan().toString();
	}
	public void add(String data) {
		this.getScan().add(data);
	}
	public void add(int i,String data) {
		this.getScan().add(i,data);
	}
	public String getNumOfNet() {
		return numOfNet;
	}
	public void setNumOfNet(String numOfNet) {
		this.numOfNet = numOfNet;
	}
	public ArrayList<String> getScan() {
		return scan;
	}
	public void setScan(ArrayList<String> scan) {
		this.scan = scan;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFreq() {
		return freq;
	}
	public void setFreq(String freq) {
		this.freq = freq;
	}
	
}
