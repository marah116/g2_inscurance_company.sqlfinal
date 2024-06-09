
public class Phone {

	private int PhoneID;
	private String PhoneNumber;
	private String CustomerID;

	public int getPhoneID() {
		return PhoneID;
	}

	public void setPhoneID(int phoneID) {
		PhoneID = phoneID;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public Phone(int phoneID, String phoneNumber, String customerID) {
		super();
		PhoneID = phoneID;
		PhoneNumber = phoneNumber;
		CustomerID = customerID;
	}

	public Phone() {
		super();
	}

}
