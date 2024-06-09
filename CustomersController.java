import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomersController implements Initializable {

	@FXML
	private Button Add;

	@FXML
	private TableColumn<Customers, Date> BirthDateco;

	@FXML
	private Label BirthDatelab;

	@FXML
	private DatePicker Birthday1;

	@FXML
	private TableColumn<Customers, Integer> BranchIDco;

	@FXML
	private Label BranchIDlab;

	@FXML
	private Label CustomerIDlab;

	@FXML
	private TextField CustomerIDtex;

	@FXML
	private Button Delete;

	@FXML
	private TableColumn<Customers, String> Emailco;

	@FXML
	private Label Emaillab;

	;

	@FXML
	private TableColumn<Customers, String> Genderco;

	@FXML
	private Label Genderlab;

	@FXML
	private Button GoToBranchpage;

	@FXML
	private Button GoToHomePage;

	@FXML
	private Button GoTocustomerQureypage;

	@FXML
	private Button GoTocustomeraddresspage;

	@FXML
	private Button GoToinsurance_policies;

	@FXML
	private Button GoTophonepage;

	@FXML
	private Label List_gender;

	@FXML
	private RadioButton Male_Rad_But;

	@FXML
	private TableColumn<Customers, String> Nameco;

	@FXML
	private Button SearchByID;

	@FXML
	private TextField SearchByIDtex;

	@FXML
	private Button ShowData;

	@FXML
	private Button Update;

	@FXML
	private ComboBox<Branch> choiceBranchID;

	@FXML
	private TableColumn<Customers, String> customerIDco;

	@FXML
	private Label customernamelab;

	@FXML
	private TextField customernametex;

	@FXML
	private Label customerspage;

	@FXML
	private TableView<Customers> customertabl;

	@FXML
	private Text insurancecompany;

	@FXML
	private AnchorPane pa1;

	@FXML
	private AnchorPane pa2;

	@FXML
	private TextField Emailtex;

	@FXML
	private Button undo;

	@FXML
	private TextField Gendertex;

	private Map<String, Object> lastDeletedRecord = new HashMap<>();
	private boolean recordDeleted = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setcompobox() {
		BranchesController s = new BranchesController();
		ObservableList<Branch> branchList = s.returnBranchid();
		choiceBranchID.setItems(branchList);
	}

	public void goToHome() {
		Stage stage = (Stage) GoToHomePage.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("BranchList.fxml"));
			Scene scene = new Scene(root, 900, 800);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void goToCustomerQuery() {
		Stage stage = (Stage) GoTocustomerQureypage.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("BranchList.fxml"));
			Scene scene = new Scene(root, 900, 800);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void goToPhone() {
		Stage stage = (Stage) GoTophonepage.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("BranchList.fxml"));
			Scene scene = new Scene(root, 900, 800);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void goToAddres() {
		Stage stage = (Stage) GoTocustomeraddresspage.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("BranchList.fxml"));
			Scene scene = new Scene(root, 900, 800);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void goTopolicis() {
		Stage stage = (Stage) GoToinsurance_policies.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("BranchList.fxml"));
			Scene scene = new Scene(root, 900, 800);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void goToBranch() {
		Stage stage = (Stage) GoToBranchpage.getScene().getWindow();
		stage.close();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("BranchList.fxml"));
			Scene scene = new Scene(root, 900, 800);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void handleRowSelection(MouseEvent event) {
		int index = customertabl.getSelectionModel().getSelectedIndex();
		if (index <= -1) {
			return;
		}

		Customers selectedCustomers = customertabl.getSelectionModel().getSelectedItem();
		if (selectedCustomers != null) {
			CustomerIDtex.setText(selectedCustomers.getCustomerID());
			customernametex.setText(selectedCustomers.getCName());
			Birthday1.setValue(((java.sql.Date) selectedCustomers.getDateOfBirth()).toLocalDate());
			Emailtex.setText(selectedCustomers.getCName());
			Gendertex.setText(selectedCustomers.getGender());
			choiceBranchID.setValue(new Branch(selectedCustomers.getBranchID(), "", index));
		}
	}

	public ObservableList<Customers> getCustomers(String query) {
		ObservableList<Customers> branchList = FXCollections.observableArrayList();
		try (Connection conn = DataBaseConnector.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query)) {

			while (rs.next()) {
				Customers customers = new Customers(rs.getString("CustomersID"), rs.getString("Gender"),
						rs.getString("CName"), rs.getDate("DateOfBirth"), rs.getString("Email"), rs.getInt("BranchID"));
				branchList.add(customers);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return branchList;
	}

	public void showCustomers() {
		String query = "SELECT * FROM g2_vehicle_insurance_company.Branches";
		ObservableList<Customers> CustomershList = getCustomers(query);
		customerIDco.setCellValueFactory(new PropertyValueFactory<>("BranchID"));
		Nameco.setCellValueFactory(new PropertyValueFactory<>("BranchName"));
		Genderco.setCellValueFactory(new PropertyValueFactory<>("SiteID"));
		BirthDateco.setCellValueFactory(new PropertyValueFactory<>("SiteID"));
		Emailco.setCellValueFactory(new PropertyValueFactory<>("SiteID"));
		BranchIDco.setCellValueFactory(new PropertyValueFactory<>("SiteID"));
		customertabl.setItems(CustomershList);
	}

	public void insertRecord() {
		try {
			int costomerID = Integer.parseInt(CustomerIDtex.getText().trim());

			if (customerIDExists(costomerID)) {
				displayAlert("costomerID already exists.");
				return;
			}

			String query = "INSERT INTO g2_vehicle_insurance_company.customers (costomerID, Gender, CName,DateOfBirth,Email,BranchID) VALUES (?, ?, ?,?,?,?)";
			Connection conn = DataBaseConnector.getConnection();

			try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
				preparedStatement.setInt(1, costomerID);
				preparedStatement.setString(2, Gendertex.getText());
				preparedStatement.setString(3, customernametex.getText());
				preparedStatement.setDate(5, java.sql.Date.valueOf(Birthday1.getValue()));
				preparedStatement.setString(6, Emailtex.getText());
				preparedStatement.setInt(4, choiceBranchID.getValue().getBranchID());

				preparedStatement.executeUpdate();
			}

			showCustomers();
		} catch (NumberFormatException e) {
			displayAlert("Invalid costomerID. Please enter a valid integer.");
		} catch (SQLException e) {
			e.printStackTrace();
			displayAlert("Error executing the insert operation.");
		}
	}

	private boolean customerIDExists(int costomerID) {
		String query = "SELECT COUNT(*) FROM g2_vehicle_insurance_company.customers WHERE costomerID = " + costomerID;
		try (Connection conn = DataBaseConnector.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query)) {

			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private void displayAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
