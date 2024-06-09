//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.Text;
//
//public class PhoneController implements Initializable {
//	@FXML
//    private Button Add;
//
//    @FXML
//    private ComboBox<?> CustomerIDChoice;
//
//    @FXML
//    private Label CustomerIDLab;
//
//    @FXML
//    private TableColumn<?, ?> CustomerIDco;
//
//    @FXML
//    private Button Delete;
//
//    @FXML
//    private Button GoToCustomerPage1;
//
//    @FXML
//    private Button GoToHomePage;
//
//    @FXML
//    private Button GoToPhonePage;
//
//    @FXML
//    private Label List_gender;
//
//    @FXML
//    private Label PhoneForCustomer;
//
//    @FXML
//    private Label PhoneIDLab;
//
//    @FXML
//    private TextField PhoneIDTex;
//
//    @FXML
//    private TextField PhoneIDToSearch;
//
//    @FXML
//    private TableColumn<?, ?> PhoneIDco;
//
//    @FXML
//    private Label PhoneNumberLab;
//
//    @FXML
//    private TextField PhoneNumberTex;
//
//    @FXML
//    private TableColumn<?, ?> PhoneNumberco;
//
//    @FXML
//    private TableView<?> PhoneTab;
//
//    @FXML
//    private Button SearchByID;
//
//    @FXML
//    private Button ShowData;
//
//    @FXML
//    private Button UnDoDelete;
//
//    @FXML
//    private Text insurancecompany;
//
//    @FXML
//    private AnchorPane pa1;
//
//    @FXML
//    private AnchorPane pa2;
//
//    @FXML
//    private Button update;
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//
//	}
	import java.net.URL;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
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
	import javafx.scene.control.Label;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextField;
	import javafx.scene.control.cell.PropertyValueFactory;
	import javafx.scene.input.MouseEvent;
	import javafx.scene.layout.AnchorPane;
	import javafx.stage.Stage;

	public class BranchesController implements Initializable {

		@FXML
		private TableColumn<Branch, Integer> BranchID;

		@FXML
		private TableColumn<Branch, String> BranchName;

		@FXML
		private TableColumn<Sites, Integer> SiteIDco;

		@FXML
		private TableView<Branch> BranchesTableView;

		@FXML
		private Button add;

		@FXML
		private Button delete;

		@FXML
		private Button undo;

		@FXML
		private Button update;

		@FXML
		private Button search;
		@FXML
		private Button ShowData;

		@FXML
		private Button goToBasePage;

		@FXML
		private Button goToBranchList;

		@FXML
		private Button goToSitesPage;

		@FXML
		private TextField BranchIDLab;

		@FXML
		private TextField BranchNameLab;

		@FXML
		private ComboBox<Sites> choiceSitesID;

		@FXML
		private TextField siteidTetosearch;

		@FXML
		private Label BranchIDTe;

		@FXML
		private Label BranchNameTe;

		@FXML
		private Label SiteIDTe;

		@FXML
		private AnchorPane anchorPane22;

		@FXML
		private AnchorPane anchorPane2;

		private Map<String, Object> lastDeletedRecord = new HashMap<>();
		private boolean recordDeleted = false;

		@Override
		public void initialize(URL url, ResourceBundle rb) {
			showBranches();
			setcompobox();
		}

		public void setcompobox() {
		    SitesController s = new SitesController();
		    ObservableList<Sites> SitesList = s.returnsitesid();
		    choiceSitesID.setItems(SitesList);
		}


		public void goToBranchList() {
			Stage stage = (Stage) goToBranchList.getScene().getWindow();
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

		public void goToBasePage() {
			Stage stage = (Stage) goToBasePage.getScene().getWindow();
			stage.close();

			try {
				Parent root = FXMLLoader.load(getClass().getResource("BasePage.fxml"));
				Scene scene = new Scene(root, 900, 800);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		public void handleRowSelection(MouseEvent event) {
			int index = BranchesTableView.getSelectionModel().getSelectedIndex();
			if (index <= -1) {
				return;
			}

			Branch selectedBranch = BranchesTableView.getSelectionModel().getSelectedItem();
			if (selectedBranch != null) {
				BranchIDLab.setText(String.valueOf(selectedBranch.getBranchID()));
				BranchNameLab.setText(selectedBranch.getBranchName());
				// Assuming Sites is the class for Site information
				choiceSitesID.setValue(new Sites(selectedBranch.getSiteID(), "", ""));
			}
		}

		public ObservableList<Branch> getBranches(String query) {
			ObservableList<Branch> branchList = FXCollections.observableArrayList();
			try (Connection conn = DataBaseConnector.getConnection();
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query)) {

				while (rs.next()) {
					Branch branch = new Branch(rs.getInt("BranchID"), rs.getString("BranchName"), rs.getInt("SiteID"));
					branchList.add(branch);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return branchList;
		}

		public void showBranches() {
			String query = "SELECT * FROM g2_vehicle_insurance_company.Branches";
			ObservableList<Branch> branchList = getBranches(query);
			BranchID.setCellValueFactory(new PropertyValueFactory<>("BranchID"));
			BranchName.setCellValueFactory(new PropertyValueFactory<>("BranchName"));
			SiteIDco.setCellValueFactory(new PropertyValueFactory<>("SiteID"));
			BranchesTableView.setItems(branchList);
		}

		public void insertRecord() {
			try {
				int branchID = Integer.parseInt(BranchIDLab.getText().trim());

				if (branchIDExists(branchID)) {
					displayAlert("BranchID already exists.");
					return;
				}

				String query = "INSERT INTO g2_vehicle_insurance_company.Branches (BranchID, BranchName, SiteID) VALUES (?, ?, ?)";
				Connection conn = DataBaseConnector.getConnection();

				try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
					preparedStatement.setInt(1, branchID);
					preparedStatement.setString(2, BranchNameLab.getText());
					preparedStatement.setInt(3, choiceSitesID.getValue().getSiteID());

					preparedStatement.executeUpdate();
				}

				showBranches();
			} catch (NumberFormatException e) {
				displayAlert("Invalid BranchID. Please enter a valid integer.");
			} catch (SQLException e) {
				e.printStackTrace();
				displayAlert("Error executing the insert operation.");
			}
		}

		private boolean branchIDExists(int branchID) {
			String query = "SELECT COUNT(*) FROM g2_vehicle_insurance_company.Branches WHERE BranchID = " + branchID;
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

		public void updateRecord() {
			try {
				int branchID = Integer.parseInt(BranchIDLab.getText().trim());
				String branchName = BranchNameLab.getText();
				int siteID = choiceSitesID.getValue().getSiteID();

				String query = "UPDATE g2_vehicle_insurance_company.Branches SET BranchName = ?, SiteID = ? WHERE BranchID = ?";
				Connection conn = DataBaseConnector.getConnection();

				try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
					preparedStatement.setString(1, branchName);
					preparedStatement.setInt(2, siteID);
					preparedStatement.setInt(3, branchID);

					preparedStatement.executeUpdate();
				}
				showBranches();
			} catch (NumberFormatException e) {
				displayAlert("Invalid BranchID. Please enter a valid integer.");
			} catch (SQLException e) {
				e.printStackTrace();
				displayAlert("Error executing the update operation.");
			}
		}

		public void deleteRecord() {
			try {
				int branchID = Integer.parseInt(BranchIDLab.getText().trim());

				// Fetching the record before deletion
				String fetchQuery = "SELECT * FROM g2_vehicle_insurance_company.Branches WHERE BranchID = ?";
				Connection conn = DataBaseConnector.getConnection();

				try (PreparedStatement fetchStatement = conn.prepareStatement(fetchQuery)) {
					fetchStatement.setInt(1, branchID);
					ResultSet rs = fetchStatement.executeQuery();
					if (rs.next()) {
						lastDeletedRecord.put("BranchID", rs.getInt("BranchID"));
						lastDeletedRecord.put("BranchName", rs.getString("BranchName"));
						lastDeletedRecord.put("SiteID", rs.getInt("SiteID"));
						recordDeleted = true;
					}
				}

				// Deleting the record
				String query = "DELETE FROM g2_vehicle_insurance_company.Branches WHERE BranchID = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
					preparedStatement.setInt(1, branchID);
					preparedStatement.executeUpdate();
				}

				showBranches();
			} catch (NumberFormatException e) {
				displayAlert("Invalid BranchID. Please enter a valid integer.");
			} catch (SQLException e) {
				e.printStackTrace();
				displayAlert("Error executing the delete operation.");
			}
		}

		public void undoDelete() {
			if (recordDeleted && !lastDeletedRecord.isEmpty()) {
				String query = "INSERT INTO g2_vehicle_insurance_company.Branches (BranchID, BranchName, SiteID) VALUES (?, ?, ?)";
				Connection conn = DataBaseConnector.getConnection();

				try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
					preparedStatement.setInt(1, (int) lastDeletedRecord.get("BranchID"));
					preparedStatement.setString(2, (String) lastDeletedRecord.get("BranchName"));
					preparedStatement.setInt(3, (int) lastDeletedRecord.get("SiteID"));

					preparedStatement.executeUpdate();
					recordDeleted = false;
					lastDeletedRecord.clear();
					showBranches();
				} catch (SQLException e) {
					e.printStackTrace();
					displayAlert("Error executing the undo operation.");
				}
			} else {
				displayAlert("No record to undo.");
			}
		}

		public void searchRecord() {
			String query = "SELECT * FROM g2_vehicle_insurance_company.Branches WHERE BranchID = ?";
			try (Connection conn = DataBaseConnector.getConnection();
					PreparedStatement preparedStatement = conn.prepareStatement(query)) {

				preparedStatement.setInt(1, Integer.parseInt(siteidTetosearch.getText().trim()));
				ResultSet rs = preparedStatement.executeQuery();

				ObservableList<Branch> branchList = FXCollections.observableArrayList();
				while (rs.next()) {
					Branch branch = new Branch(rs.getInt("BranchID"), rs.getString("BranchName"), rs.getInt("SiteID"));
					branchList.add(branch);
				}
				BranchesTableView.setItems(branchList);
			} catch (SQLException e) {
				e.printStackTrace();
				displayAlert("Error executing the search operation.");
			}
		}

		public ObservableList<Branch> returnBranchid() {
		    String query = "SELECT b.BranchID FROM branches s";
		    ObservableList<Branch> branchidList = FXCollections.observableArrayList();

		    try (Connection conn = DataBaseConnector.getConnection();
		         Statement st = conn.createStatement();
		         ResultSet rs = st.executeQuery(query)) {

		        if (!rs.isBeforeFirst()) {
		            System.out.println("No records found for the query: " + query);
		            return FXCollections.observableArrayList(); // Return an empty list
		        } else {
		            while (rs.next()) {
		            	Branch branch = new Branch(rs.getInt("BranchID"));
		                branchidList.add(branch);
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }

		    return branchidList;
		}
	}

}
