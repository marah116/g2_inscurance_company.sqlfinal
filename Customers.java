import java.util.Date;

public class Customers {

	private String CustomerID;
	private String Gender;
	private String CName;
	private Date DateOfBirth;
	private String Email;
	private int BranchID;

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	public Customers(String customerID, String gender, String cName, Date dateOfBirth, String email, int branchID) {
		super();
		CustomerID = customerID;
		Gender = gender;
		CName = cName;
		DateOfBirth = dateOfBirth;
		Email = email;
		BranchID = branchID;
	}

	public Customers() {
		super();
	}

}
