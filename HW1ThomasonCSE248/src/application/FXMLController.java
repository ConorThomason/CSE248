package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLController {
	UserAccountBag userBag = new UserAccountBag();
	@FXML
	private JFXTextField signUser;
	
	@FXML
	private Label currentActionLabel;
	
	@FXML
	private JFXPasswordField signPassword;
	
	@FXML
	private JFXTextField loggedUserOutput;
	
	@FXML
	private JFXTextField loggedFirstNameOutput;
	
	@FXML
	private JFXTextField loggedLastNameOutput;
	
	@FXML
	private JFXTextField loggedIDOutput;
	
	@FXML
	private JFXTextField loggedGenderOutput;
	
	@FXML
	private JFXTextField loggedGPAOutput;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private JFXButton signConfirm;
	
	@FXML
	private JFXButton signInToggle;
	
	@FXML
	private JFXButton signUpToggle;
	
	@FXML
	private Label detailsDescription;
	
	
	public void initialize() {
		userBag.padUsers();
    }

	private void labelChange(String message) {
		statusLabel.setText(message);
	}
	
	public void handleSignButtonChangeToggle(ActionEvent event) {
		boolean isDisabled = signInToggle.isDisabled();
		signInToggle.setDisable(!isDisabled);
		signUpToggle.setDisable(isDisabled);
		editableFieldToggle(isDisabled); //If sign in is now not disabled, the fields will not be.
		disableFieldToggle(isDisabled);
		updateFields();
	}
	public void handleConfirmButtonAction(ActionEvent event) {
		if (!signInToggle.isDisabled()) {
			signUp();
			handleSignButtonChangeToggle(event);
		}
		else {
			signIn();
		}
	}
	private void signIn() {
		if (userBag.searchAccount(signUser.getText(), signPassword.getText())) {
			labelChange("Successful login as " + signUser.getText());
			userBag.setCurrentUser(userBag.findUser(signUser.getText()));
			emptyLoginFields();
			updateFields();
		}
		else {
			labelChange("User Name not found/Password incorrect. Please try again");
			emptyLoginFields();
			updateFields();
		}
	}
	private void signUp() {
		String firstName = loggedFirstNameOutput.getText();
		String lastName = loggedLastNameOutput.getText();
		String gender = userBag.getAccountFactory().verifyGenderInput(loggedGenderOutput.getText());
		double gpa = userBag.getAccountFactory().verifyGpaInput(Double.parseDouble(loggedGPAOutput.getText()));
		String password = signPassword.getText();
		if (userBag.getAccountFactory().verifyPassword(password) && !gender.equals("Invalid") && gpa != -1.0) {
		User newUser = userBag.createUser(firstName, lastName, gender, gpa, password);
		if (userBag.insertUser(newUser)) {
			labelChange("Successful creation of User Name " + newUser.getUserName());
		} else if (userBag.getAccountFactory().verifyPassword(password)) {
			labelChange("Unsuccessful creation of User");
		}}
		else {
			labelChange("Invalid input in one or more fields; please check entries");
		}
		
	}
	private void emptyLoginFields() {
		signUser.setText("");
		signPassword.setText("");
	}
	private void updateFields() {
		emptyFields();
		if (userBag.getCurrentUser() != null) {
			User currentUserClone = userBag.getCurrentUser();
			loggedUserOutput.setText(currentUserClone.getUserName());
			loggedFirstNameOutput.setText(currentUserClone.getFirstName());
			loggedLastNameOutput.setText(currentUserClone.getLastName());
			loggedGenderOutput.setText(currentUserClone.getGender());
			loggedGPAOutput.setText(Double.toString(currentUserClone.getGpa()));
			loggedIDOutput.setText(currentUserClone.getId());
		}
	}
	private void emptyFields() {
		loggedUserOutput.setText("");
		loggedFirstNameOutput.setText("");
		loggedLastNameOutput.setText("");
		loggedGenderOutput.setText("");
		loggedGPAOutput.setText("");
		loggedIDOutput.setText("");
	}
	public void updateUserField(Event event) {
		try {
		if (loggedFirstNameOutput.getText() != null && loggedLastNameOutput.getText() != null) {
			loggedUserOutput.setText(userBag.emitUserName(loggedFirstNameOutput.getText(), 
					loggedLastNameOutput.getText(), Integer.toString(userBag.getId())));
			loggedIDOutput.setText(Integer.toString(userBag.getId()));
		}
		else if (!signInToggle.isDisabled()) {
			loggedUserOutput.setText(userBag.getCurrentUser().getUserName());
		}
		else
			loggedUserOutput.setText("");
		} catch (StringIndexOutOfBoundsException e) {
			//nop
		}
	}
	private void disableFieldToggle(boolean editable) {
		if (editable) {
			signUser.setVisible(false);
			detailsDescription.setText("Enter User Details For Registration");
			currentActionLabel.setText("Sign up");
		}
		else {
			signUser.setVisible(true);
			currentActionLabel.setText("Sign in");
			detailsDescription.setText("Current User Details");
		}
		loggedFirstNameOutput.setDisable(!editable);
		loggedLastNameOutput.setDisable(!editable);
		loggedGenderOutput.setDisable(!editable);
		loggedGPAOutput.setDisable(!editable);
	}
	private void editableFieldToggle(boolean editable) {
		loggedFirstNameOutput.setEditable(editable);
		loggedLastNameOutput.setEditable(editable);
		loggedGenderOutput.setEditable(editable);
		loggedGPAOutput.setEditable(editable);
	}
	
	
}
