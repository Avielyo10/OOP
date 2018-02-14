package writing;

import java.io.File;
import java.io.IOException;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import wifi.wifi_scans;

/**
 * writing wifi scans to kml
 * @author Aviel
 *
 */
public class toKml {
	/**
	 * take the DS of final top ten csv and creates kml file
	 * @param dataLists
	 * @throws IOException
	 */
	public static void writeToKml(wifi_scans dataLists, String fileName) throws IOException {
		if(!fileName.endsWith(".kml")) {
			fileName+=".kml";
		}
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument().withName("newKmls");
		for (int i = 0; i < dataLists.size(); i++) {
			int _numHop = Integer.valueOf(dataLists.get(i).getNumOfNet())*4;
			for (int j = 0; j < _numHop; j+=4) {
				Placemark placemark = doc.createAndAddPlacemark().withName(dataLists.get(i).getScan().get(6+j).replaceAll("&", "&amp;"));
				placemark.createAndSetPoint()
				.addToCoordinates(Double.parseDouble(dataLists.get(i).getLon()), Double.parseDouble(dataLists.get(i).getLat()));
				placemark.createAndSetTimeStamp().withWhen(dataLists.get(i).getTime());
			}
		}
		kml.marshal(new File(fileName));
	}
}
