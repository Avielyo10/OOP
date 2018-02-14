package GUI;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.util.Duration;
import writing.toCsv;
import writing.toKml;
public class filePopUp {
	static String _path = "Empty.";
	static int numOfNet = wrapper.current.size();
	static boolean isChanged = false;
	/**
	 * the upload pop up 
	 */
	public static void uploadPop() throws IOException {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,500,100);
		TextField path = new TextField("Enter Path..");
		Button uploadB = new Button("_Upload");
		pop.initModality(Modality.APPLICATION_MODAL);
		layout.setVgap(30);
		layout.setHgap(10);
		uploadB.setOnAction(e -> {
			_path = path.getText();
			TimerTask check = new DirWatcher(_path, ".csv" ) {
				@Override
				protected void onChange(File file, String action) throws IOException {
					isChanged = true;
				}
			};
			Timer timer = new Timer();
		    timer.schedule(check,new Date(),1000);
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
				if(isChanged) {
					try {
						wrapper.refresh();
						isChanged = false;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
			try {
				numOfNet = wrapper.size(wrapper.setScan(_path));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Main.setStatus();
			pop.close();
		});
		path.setPrefWidth(400);
		layout.add(path, 1, 1);
		layout.add(uploadB, 2, 1);
		pop.setScene(scene);
		pop.setTitle("Upload");
		pop.showAndWait();
	}
	/**
	 * Saving to csv pop up
	 */
	public static void saveCsvPop() {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,500,100);
		Button csvB = new Button("_Save");
		TextField name = new TextField("New Csv");
		csvB.setOnAction(e -> {
			try {
				toCsv.writeToCsv(wrapper.current,name.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			pop.close();
		});
		layout.setVgap(30);
		layout.setHgap(10);
		name.setPrefWidth(400);
		layout.add(name, 1, 1);
		layout.add(csvB, 2, 1);
		pop.initModality(Modality.APPLICATION_MODAL);
		pop.setScene(scene);
		pop.setTitle("Save as Csv");
		pop.showAndWait();
	}
	/**
	 * saving to kml pop up
	 */
	public static void saveKmlPop() {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,500,100);
		Button kmlB = new Button("_Save");
		TextField name = new TextField("New Kml");
		kmlB.setOnAction(e -> {
			try {
				toKml.writeToKml(wrapper.current,name.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			pop.close();
		});
		layout.setVgap(30);
		layout.setHgap(10);
		name.setPrefWidth(400);
		layout.add(name, 1, 1);
		layout.add(kmlB, 2, 1);
		pop.initModality(Modality.APPLICATION_MODAL);
		pop.setScene(scene);
		pop.setTitle("Save as Kml");
		pop.showAndWait();
	}
	/**
	 * clear pop up
	 */
	public static void clearPop() {
		Stage pop = new Stage();
		GridPane layout = new GridPane();
		Scene scene = new Scene(layout,400,100);
		Label clear = new Label("Are You Sure?");
		Button no = new Button("No");
		Button ok = new Button("Yes");
		ok.setOnAction(e -> {
			wrapper.clearScan();
			_path = "Empty.";
			numOfNet = 0;
			Main.setStatus();
			pop.close();
		});
		no.setOnAction(e -> pop.close());
		clear.setTranslateY(10);
		clear.setTranslateX(150);
		no.setTranslateX(200);
		no.setTranslateY(50);
		ok.setTranslateX(150);
		ok.setTranslateY(50);
		pop.initModality(Modality.APPLICATION_MODAL);
		layout.getChildren().addAll(ok,no,clear);
		pop.setScene(scene);
		pop.setTitle("Clear Data Base");
		pop.showAndWait();
	}
}
