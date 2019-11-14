package acamo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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

        // TODO: Create layout of table and pane for selected aircraft
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(0, 0, 0, 10));
        vBox.getChildren().add(table);

        // TODO: Add event handler for selected aircraft


		Scene scene = new Scene(vBox,800,300);
        stage.setScene(scene);
        stage.setTitle("Acamo");
        stage.sizeToScene();
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
    }

    // TODO: When messer updates Acamo (and activeAircrafts) the aircraftList must be updated as well
    @Override
    public void update(Observable o, Object arg) {
        aircraftList.add((BasicAircraft) arg);
    }
}
