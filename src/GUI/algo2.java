package GUI;

import java.util.ArrayList;
import java.util.Arrays;

import algos.algo_2DS;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wifi.wifi_scan;
import wifi.wifi_scans;

public class algo2 {

	/**
	 * show estimated location by string of data to fix
	 */
	public static void algo2StrPop() {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,500,150);
		TextField stringField = new TextField("Enter String Scan..");
		Button ok = new Button("_Enter");
		Label info = new Label();
		pop.initModality(Modality.APPLICATION_MODAL);
		layout.setVgap(30);
		layout.setHgap(10);
		ok.setOnAction(e -> {
			wifi_scan algo2Scan = new wifi_scan();
			ArrayList<String> scan =new ArrayList<String>(Arrays.asList(stringField.getText().split(",")));
			algo2Scan.setScan(scan);
			algo2Scan.setTime(scan.get(0));
			algo2Scan.setId(scan.get(1));
			algo2Scan.setLat(scan.get(2));
			algo2Scan.setLon(scan.get(3));
			algo2Scan.setAlt(scan.get(4));
			algo2Scan.setNumOfNet(scan.get(5));
			wifi_scans toFix = new wifi_scans();
			toFix.add(algo2Scan);
			algo_2DS.find(wrapper.current, toFix);
			info.setText("Lat: "+toFix.get(0).getLat()+" Lon: "+ toFix.get(0).getLon()+" Alt: "+ toFix.get(0).getAlt());
		});
		stringField.setPrefWidth(400);
		layout.add(stringField, 1, 1);
		layout.add(ok, 2, 1);
		layout.add(info, 1, 2);
		pop.setScene(scene);
		pop.setTitle("Set String");
		pop.showAndWait();
	}
	/**
	 * show estimated location by macs && signals
	 */
	public static void algo2MacPop() {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,550,300);
		Label mac1L = new Label("MAC 1: ");
		Label sig1L = new Label("Signal 1: ");
		Label mac2L = new Label("MAC 2: ");
		Label sig2L = new Label("Signal 2: ");
		Label mac3L = new Label("MAC 3: ");
		Label sig3L = new Label("Signal 3: ");
		TextField mac1 = new TextField();
		TextField mac2 = new TextField();
		TextField mac3 = new TextField();
		TextField sig1 = new TextField();
		TextField sig2 = new TextField();
		TextField sig3 = new TextField();
		Button insert = new Button("Enter");
		Label info = new Label();
		mac1L.setTranslateX(40);
		mac1L.setTranslateY(10);
		sig1L.setTranslateX(300);
		sig1L.setTranslateY(10);
		mac1.setTranslateX(40);
		mac1.setTranslateY(40);
		sig1.setTranslateX(300);
		sig1.setTranslateY(40);
		mac2L.setTranslateX(40);
		mac2L.setTranslateY(70);
		sig2L.setTranslateX(300);
		sig2L.setTranslateY(70);
		mac2.setTranslateX(40);
		mac2.setTranslateY(100);
		sig2.setTranslateX(300);
		sig2.setTranslateY(100);
		mac3L.setTranslateX(40);
		mac3L.setTranslateY(130);
		sig3L.setTranslateX(300);
		sig3L.setTranslateY(130);
		mac3.setTranslateX(40);
		mac3.setTranslateY(160);
		sig3.setTranslateX(300);
		sig3.setTranslateY(160);
		insert.setTranslateX(40);
		insert.setTranslateY(200);
		info.setTranslateX(40);
		info.setTranslateY(250);
		layout.getChildren().addAll(info,insert,mac1L,sig1L,mac2L,sig2L,mac3L,sig3L,mac1,mac2,mac3,sig1,sig2,sig3);
		insert.setOnAction(e ->{
			wifi_scan algo2Scan = new wifi_scan();
			ArrayList<String> scan =new ArrayList<String>();
			for (int i = 0; i < 17; i++) {
				scan.add(null);
			}
			int counter = 0;
			if(mac1.getText().length()!=0&&sig1.getText().length()!=0) {
				scan.add(7,mac1.getText());
				scan.add(9,sig1.getText());
				counter++;
			}
			if(mac2.getText().length()!=0&&sig2.getText().length()!=0) {
				scan.add(11,mac2.getText());
				scan.add(13,sig2.getText());
				counter++;
			}
			if(mac3.getText().length()!=0&&sig3.getText().length()!=0) {
				scan.add(15,mac3.getText());
				scan.add(17,sig3.getText());
				counter++;
			}
			algo2Scan.setNumOfNet(String.valueOf(counter));
			algo2Scan.setScan(scan);
			wifi_scans toFix = new wifi_scans();
			toFix.add(algo2Scan);
			algo_2DS.find(wrapper.current, toFix);
			info.setText("Lat: "+toFix.get(0).getLat()+" Lon: "+ toFix.get(0).getLon()+" Alt: "+ toFix.get(0).getAlt());
		});
		pop.initModality(Modality.APPLICATION_MODAL);
		pop.setScene(scene);
		pop.setTitle("Set MAC");
		pop.showAndWait();
	}
}
