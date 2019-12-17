package ch.makery.eventschool.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.eventschool.model.EventSchool;
import ch.makery.eventschool.util.DateUtil;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class EventSchoolEditDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField typeEventField;
    @FXML
    private TextField schoolSubjectField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField eventDateField;


    private Stage dialogStage;
    private EventSchool eventSchool;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setEventSchool(EventSchool eventSchool) {
        this.eventSchool = eventSchool;

        titleField.setText(eventSchool.getTitle());
        typeEventField.setText(eventSchool.getTypeEvent());
        schoolSubjectField.setText(eventSchool.getSchoolSubject());
        descriptionField.setText(eventSchool.getDescription());
        eventDateField.setText(DateUtil.format(eventSchool.getEventDate()));
        eventDateField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	
        	eventSchool.setTitle(titleField.getText());
        	eventSchool.setTypeEvent(typeEventField.getText());
        	eventSchool.setSchoolSubject(schoolSubjectField.getText());
        	eventSchool.setDescription(descriptionField.getText());
        	eventSchool.setEventDate(DateUtil.parse(eventDateField.getText()));
          
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "Entre com o Titulo!\n";
        }
        if (typeEventField.getText() == null || typeEventField.getText().length() == 0) {
            errorMessage += "Entre com o tipo de evento\n";
        }
        if (schoolSubjectField.getText() == null || schoolSubjectField.getText().length() == 0) {
            errorMessage += "Entre com a matéria \n";
        }
        if (descriptionField.getText() == null || descriptionField.getText().length() == 0) {
            errorMessage += "Entre com a descrição\n";
        }

        if (eventDateField.getText() == null || eventDateField.getText().length() == 0) {
            errorMessage += "Entre com uma data\n";
        } else {
            if (!DateUtil.validDate(eventDateField.getText())) {
                errorMessage += "Formato invalido use dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erros Encontrados");
            alert.setHeaderText("Corriga os Apontamentos");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}