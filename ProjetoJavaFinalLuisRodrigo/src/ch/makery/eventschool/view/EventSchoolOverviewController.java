package ch.makery.eventschool.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.eventschool.MainApp;
import ch.makery.eventschool.model.EventSchool;
import ch.makery.eventschool.util.DateUtil;

public class EventSchoolOverviewController {

    @FXML
    private TableView<EventSchool> eventSchoolarTable;
    @FXML
    private TableColumn<EventSchool, String> titleColumn;
    @FXML
    private TableColumn<EventSchool, String> typeEventColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label typeEventLabel;
    @FXML
    private Label schoolSubjectLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label eventDateLabel;


    private MainApp mainApp;


    public EventSchoolOverviewController() {
    }


    @FXML
    private void initialize() {

    	titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    	typeEventColumn.setCellValueFactory(cellData -> cellData.getValue().typeEventProperty());


        showEventSchoolarDetails(null);


        eventSchoolarTable.getSelectionModel().selectedItemProperty().addListener
        ((observable, oldValue, newValue) -> showEventSchoolarDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        eventSchoolarTable.setItems(mainApp.getEventSchoolData());
    }


    private void showEventSchoolarDetails(EventSchool eventSchool) {
        if (eventSchool != null) {

            titleLabel.setText(eventSchool.getTitle());
            typeEventLabel.setText(eventSchool.getTypeEvent());
            schoolSubjectLabel.setText(eventSchool.getSchoolSubject());
            descriptionLabel.setText(eventSchool.getDescription());
            eventDateLabel.setText(DateUtil.format(eventSchool.getEventDate()));
            
        } else {

        	titleLabel.setText("");
        	typeEventLabel.setText("");
        	schoolSubjectLabel.setText("");
        	descriptionLabel.setText("");
            eventDateLabel.setText("");
        }
    }


    @FXML
    private void handleDeleteEventSchool() {
        int selectedIndex = eventSchoolarTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	eventSchoolarTable.getItems().remove(selectedIndex);
        } else {
            // Alertas.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ninguem Selecionado");
            alert.setHeaderText("Nenhum Evento Selecionada");
            alert.setContentText("Por Favor selecione algo da tabela.");

            alert.showAndWait();
        }
    }


    @FXML
    private void handleNewEventSchool() {
        EventSchool tempEventSchool = new EventSchool();
        boolean okClicked = mainApp.showEventSchoolEditDialog(tempEventSchool);
        if (okClicked) {
            mainApp.getEventSchoolData().add(tempEventSchool);
        }
    }


    @FXML
    private void handleEditEventSchool() {
        EventSchool selectedEventSchool = eventSchoolarTable.getSelectionModel().getSelectedItem();
        if (selectedEventSchool != null) {
            boolean okClicked = mainApp.showEventSchoolEditDialog(selectedEventSchool);
            if (okClicked) {
                showEventSchoolarDetails(selectedEventSchool);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nada Selecionado");
            alert.setHeaderText("Nenhum Evento Selecionada");
            alert.setContentText("Por Favor selecione algo da tabela.");

            alert.showAndWait();
        }
    }
}