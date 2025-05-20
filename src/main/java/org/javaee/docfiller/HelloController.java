package org.javaee.docfiller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HelloController {


    @FXML
    private ComboBox<String> documentTypeComboBox;

    @FXML
    private TableColumn<ObjectUsers, Integer> id;

    @FXML
    private TableColumn<ObjectUsers, String> firstName;

    @FXML
    private TableColumn<ObjectUsers, String> lastName;

    @FXML
    private TableView<ObjectUsers> table_users;

    @FXML
    private TextArea textArea;

    FileChooser fileChooser = new FileChooser();

    @FXML
    void save(MouseEvent event) {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            saveSystem(file, textArea.getText());
        }
    }

    public void saveSystem(File file, String content) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(content);
            printWriter.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        documentTypeComboBox.setItems(FXCollections.observableArrayList("Invitation", "Business Card", "Job Application Letter"));

        documentTypeComboBox.setValue("Invitation");

        ObservableList<ObjectUsers> objectUsers = FXCollections.observableArrayList();
        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.getConnection();
        String sql = "SELECT * FROM `people`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.isBeforeFirst()) {
            System.out.println("No data found in the database.");
        }

        while (resultSet.next()) {
            objectUsers.add(new ObjectUsers(
                    resultSet.getInt("idpeople"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"),
                    resultSet.getString("company"),
                    resultSet.getString("jobPosition")
            ));
        }

        id.setCellValueFactory(new PropertyValueFactory<>("idpeople"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table_users.setItems(objectUsers);

        documentTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateInvitationText(newValue);
        });

        table_users.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateInvitationText(documentTypeComboBox.getValue(), newValue);
            }
        });
    }

    private void updateInvitationText(String documentType) {
        ObjectUsers selectedUser = table_users.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            textArea.setText("Please, select a user from the table!");
            return;
        }

        updateInvitationText(documentType, selectedUser);
    }

    private void updateInvitationText(String documentType, ObjectUsers selectedUser) {
        String invitationText = "";

        if ("Invitation".equals(documentType)) {
            invitationText = "Dear " + selectedUser.getFirstName() + " " + selectedUser.getLastName() + ",\n\n"
                    + "We are pleased to invite you to our event, which will take place on [DATE] at [TIME] at [VENUE]..\n"
                    + "This will be a great opportunity for all attendees to discuss [EVENT TOPIC OR PURPOSE] and to gain new ideas for future development.\n\n"
                    + "We would be delighted to see you among our guests!\n\n"
                    + "Sincerely,\n[Your Name]\n[Organization/Company]";
        } else if ("Business Card".equals(documentType)) {
            invitationText = "Business Card for " + selectedUser.getFirstName() + " " + selectedUser.getLastName() + ":\n\n"
                    + "Job position: " + selectedUser.getJobPosition() + "\n"
                    + "Company: " + selectedUser.getCompany() + "\n"
                    + "Mobile phone: " + selectedUser.getPhone() + "\n"
                    + "Email: " + selectedUser.getEmail() + "\n";
        }

        textArea.setText(invitationText);
    }

    @FXML
    public void onSaveButtonClick() {
        System.out.println("Save button clicked!");
    }
}



