package GUI;


import java.util.HashSet;
import java.util.LinkedHashSet;

import Filter.locFilter;
import Filter.timeFilter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class filterPopUp {
	static String id;
	static String lat;
	static String lon;
	static String date$time;
	static String date_time;
	static double radius;
	static boolean notId = false;
	static boolean notTime = false;
	static boolean notLoc = false;
	/**
	 * time pop up
	 */
	public static void timePop() {
		Main.timeFilter=true;
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,550,200);
		DatePicker startingDate = new DatePicker();
		DatePicker endDate = new DatePicker();
		Label start = new Label("Start Date: ");
		Label end = new Label("End Date: ");
		Label startTime$ = new Label("Start Time: ");
		Label endTime$ = new Label("End Time: ");
		CheckBox not = new CheckBox("Not");
		TextField startTime = new TextField();
		TextField endTime = new TextField();
		Button insert = new Button("Enter");
		start.setTranslateX(40);
		start.setTranslateY(10);
		end.setTranslateX(300);
		end.setTranslateY(10);
		startingDate.setTranslateX(40);
		startingDate.setTranslateY(40);
		endDate.setTranslateX(300);
		endDate.setTranslateY(40);
		startTime$.setTranslateX(40);
		startTime$.setTranslateY(70);
		endTime$.setTranslateX(300);
		endTime$.setTranslateY(70);
		startTime.setTranslateX(40);
		startTime.setTranslateY(100);
		endTime.setTranslateX(300);
		endTime.setTranslateY(100);
		insert.setTranslateX(40);
		insert.setTranslateY(150);
		not.setTranslateX(300);
		not.setTranslateY(150);
		layout.getChildren().addAll(startingDate,endDate,start,end,startTime,endTime,insert,startTime$,endTime$,not);
		insert.setOnAction(e ->{
			date$time = (String.valueOf(startingDate.getValue()))+" "+timeFilter.checkTime(startTime.getText());
			date_time = (String.valueOf(endDate.getValue()))+" "+timeFilter.checkTime(endTime.getText());
			if(!not.isSelected()) {
				notTime=false;
				wrapper.setTime(wrapper.current,date$time,date_time);
				Main.setStatus();
			}
			else {
				notTime=true;
				wrapper.setNotTime(wrapper.current, date$time, date_time);
				Main.setStatus();
			}
			pop.close();
		});
		pop.initModality(Modality.APPLICATION_MODAL);
		pop.setScene(scene);
		pop.setTitle("Set Time");
		pop.showAndWait();
	}
	/**
	 * id pop up
	 */
	public static void idPop() {
		Main.idFilter=true;
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,500,125);
		TextField idField = new TextField("Enter ID..");
		CheckBox not = new CheckBox("Not");
		Button ok = new Button("_Enter");
		pop.initModality(Modality.APPLICATION_MODAL);
		layout.setVgap(30);
		layout.setHgap(10);
		ok.setOnAction(e -> {
			String correctId = idField.getText();
			if(!idField.getText().contains("model=")) {
				correctId = "model=" + idField.getText();
			}
			HashSet<String> id$ = new LinkedHashSet<String>();
			for (int i = 0; i < wrapper.current.size(); i++) {
				id$.add(wrapper.current.get(i).getId());
			}
			if(!id$.contains(correctId)) {
				System.err.println("This ID Can't Be Found");
			}
			else {
				id = correctId;
				if(!not.isSelected()) {
					notId=false;
					wrapper.setID(wrapper.current, id);
					Main.setStatus();	
				}
				else {
					notId=true;
					wrapper.setNotID(wrapper.current, id);
					Main.setStatus();
				}
			}
			pop.close();
		});
		idField.setPrefWidth(400);
		layout.add(idField, 1, 1);
		layout.add(ok, 2, 1);
		layout.add(not, 1, 2);
		pop.setScene(scene);
		pop.setTitle("Set ID");
		pop.showAndWait();
	}
	/**
	 * location pop up
	 */
	public static void locPop() {
		Main.locFilter=true;
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,550,200);
		Label latL = new Label("Lat: ");
		Label lonL = new Label("Lon: ");
		Label r= new Label("Radius: ");
		CheckBox not = new CheckBox("Not");
		TextField enterLat = new TextField();
		TextField enterLon = new TextField();
		TextField enterR = new TextField();
		Button ok = new Button("OK");
		latL.setTranslateX(40);
		latL.setTranslateY(10);
		lonL.setTranslateX(300);
		lonL.setTranslateY(10);
		enterLat.setTranslateX(40);
		enterLat.setTranslateY(40);
		enterLon.setTranslateX(300);
		enterLon.setTranslateY(40);
		r.setTranslateX(40);
		r.setTranslateY(70);
		enterR.setTranslateX(40);
		enterR.setTranslateY(100);
		ok.setTranslateX(300);
		ok.setTranslateY(100);
		not.setTranslateX(370);
		not.setTranslateY(100);
		ok.setOnAction(e ->{
			if(locFilter.isDoubled(enterLat.getText())&&locFilter.isDoubled(enterLon.getText())) {
				lat = enterLat.getText();
				lon = enterLon.getText();
				if(locFilter.isDoubled(enterR.getText())) {
					radius = Double.parseDouble(enterR.getText());	
				}
				else {
					radius=1000;
				}
				if(!not.isSelected()) {
					notLoc=false;
					wrapper.setLoc(wrapper.current,lon,lat,radius);
					Main.setStatus();	
				}
				else {
					notLoc=true;
					wrapper.setNotLoc(wrapper.current,lon,lat,radius);
					Main.setStatus();
				}
			}
			pop.close();
		});
		layout.getChildren().addAll(latL,lonL,enterLon,enterLat,enterR,r,ok,not);
		pop.initModality(Modality.APPLICATION_MODAL);
		pop.setScene(scene);
		pop.setTitle("Set Location");
		pop.showAndWait();
	}
}
