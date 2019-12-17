package ch.makery.eventschool;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;  
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.makery.eventschool.controller.EventSchoolAddDialogController;
import ch.makery.eventschool.controller.EventSchoolOverviewController;
import ch.makery.eventschool.model.EventSchool;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    private ObservableList<EventSchool> eventData = FXCollections.observableArrayList();


    public MainApp() {
    	
    	eventData.add(new EventSchool("sobre jdbc","prova"));
        
    }


    public ObservableList<EventSchool> getEventSchoolData() {
        return eventData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de provas e trabalhos escolares");

        initRootLayout();

        showEventSchoolOverview();
    }


    public void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();


            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showEventSchoolOverview() {
        try {           
        	//Load the FXML class of eventSchool
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EventSchoolOverview.fxml"));
            AnchorPane eventSchoolarOverview = (AnchorPane) loader.load();

            // Bring the Class EventSchool.
            rootLayout.setCenter(eventSchoolarOverview);

            // Catch the Controller of Classes.
            EventSchoolOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean showEventSchoolEditDialog(EventSchool eventSchool) {
        try {
        	//Load the FXML class of eventSchool add
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EventSchoolAddDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar Evento Escolar");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            EventSchoolAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEventSchool(eventSchool);


            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}