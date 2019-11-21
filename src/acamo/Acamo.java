package acamo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jsonstream.PlaneDataServer;
import messer.BasicAircraft;
import messer.Messer;
import senser.Senser;

public class Acamo extends Application implements Observer {
    private ActiveAircrafts activeAircrafts;
    private TableView<BasicAircraft> table = new TableView<BasicAircraft>();
    private ObservableList<BasicAircraft> aircraftList = FXCollections.observableArrayList();
    private ArrayList<String> fields;

    private double latitude = 48.7433425;
    private double longitude = 9.3201122;
    private static boolean haveConnection = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
		String urlString = "https://opensky-network.org/api/states/all";
		PlaneDataServer server;

		if(haveConnection)
			server = new PlaneDataServer(urlString, latitude, longitude, 50);
		else
			server = new PlaneDataServer(latitude, longitude, 50);

		new Thread(server).start();

		Senser senser = new Senser(server);
		new Thread(senser).start();

		Messer messer = new Messer();
		senser.addObserver(messer);
		new Thread(messer).start();

		activeAircrafts = new ActiveAircrafts();

        messer.addObserver(activeAircrafts);
        messer.addObserver(this);

        fields = BasicAircraft.getAttributesNames();

		for (String attributeName : fields) {
            TableColumn<BasicAircraft, String> column = new TableColumn<BasicAircraft, String>(attributeName);
            table.getColumns().add(column);
            column.setCellValueFactory(new PropertyValueFactory<>(attributeName));
        }

		table.setItems(aircraftList);

		table.setEditable(false);
        table.autosize();

        // GUI Layout
        // Main Layout Box [hBox]
        HBox hBox = new HBox(5);

        // Left layout box [vBoxLeft] in main layout box [hBox]
        VBox vBoxLeft = new VBox(5);
        vBoxLeft.setPadding(new Insets(10, 10, 10, 10));

        Label acLbl = new Label("Active Aircrafts");
        acLbl.setFont(new Font(16));
        acLbl.setStyle("-fx-font-weight: bold;");

        vBoxLeft.getChildren().addAll(acLbl, table);

        // Right layout box [vBoxRight] in main layout box [hBox]
        VBox vBoxRight = new VBox(5);
        vBoxRight.setPadding(new Insets(10, 10, 10, 10));

        Label selectLbl = new Label("Selected Aircraft");
        selectLbl.setFont(new Font(16));
        selectLbl.setStyle("-fx-font-weight: bold;");

        // Text layout box [hBoxText] in right layout box [vBoxRight]
        HBox hBoxText = new HBox(5);

        // Left layout box [vBoxTextDesc] for text box [hBoxText] (in right layout box [vBoxRight])
        VBox vBoxTextDesc = new VBox(5);
        vBoxTextDesc.setPadding(new Insets(0, 15, 10, 0));
        // Right layout box [vBoxTextValues] for text box [hBoxText] (in right layout box [vBoxRight])
        VBox vBoxTextValues = new VBox(5);
        vBoxTextValues.setPadding(new Insets(0, 0, 10, 15));

        // List of all created text boxes for aircraft attribute values
        ArrayList<Label> txtBoxes = new ArrayList<Label>();

        // Add all text boxes
        for (String attributeName : fields) {
            Label txtFieldDesc = new Label(attributeName);
            vBoxTextDesc.getChildren().add(txtFieldDesc);

            Label txtFieldValues = new Label();
            txtBoxes.add(txtFieldValues);
            vBoxTextValues.getChildren().add(txtFieldValues);
        }

        hBoxText.getChildren().addAll(vBoxTextDesc, vBoxTextValues);
        vBoxRight.getChildren().addAll(selectLbl, hBoxText);
        hBox.getChildren().addAll(vBoxLeft, vBoxRight);

        // Event Listener for selected item
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectAc, newSelectAc) -> {
            if (newSelectAc != null) {
                ArrayList<Object> values = BasicAircraft.getAttributesValues(newSelectAc);
                for (int i = 0; i < values.size(); i++) {
                    Label txtBox = txtBoxes.get(i);
                    Object attribute = values.get(i);

                    txtBox.setText(attribute.toString());
                }
            }
        });

		Scene scene = new Scene(hBox, 1000, 400);
        stage.setScene(scene);
        stage.setTitle("Acamo");
        stage.sizeToScene();
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        BasicAircraft ac = (BasicAircraft) arg;
        if (ac == null) {
            // Clear table on seperator (null object)
            table.getItems().clear();
        } else {
            aircraftList.add(ac);
        }
    }
}
