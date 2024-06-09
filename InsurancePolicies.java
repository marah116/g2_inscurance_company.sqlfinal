
public class InsurancePolicies {
	private int PolicyID;
	private String PolicyNumber;
	private String PolicyType;
	private String StartDate;
	private String EndDate;
	private String PremiumAmount;
	private String CustomerID;

	public int getPolicyID() {
		return PolicyID;
	}

	public void setPolicyID(int policyID) {
		PolicyID = policyID;
	}

	public String getPolicyNumber() {
		return PolicyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		PolicyNumber = policyNumber;
	}

	public String getPolicyType() {
		return PolicyType;
	}

	public void setPolicyType(String policyType) {
		PolicyType = policyType;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getPremiumAmount() {
		return PremiumAmount;
	}

	public void setPremiumAmount(String premiumAmount) {
		PremiumAmount = premiumAmount;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public InsurancePolicies(int policyID, String policyNumber, String policyType, String startDate, String endDate,
			String premiumAmount, String customerID) {
		super();
		PolicyID = policyID;
		PolicyNumber = policyNumber;
		PolicyType = policyType;
		StartDate = startDate;
		EndDate = endDate;
		PremiumAmount = premiumAmount;
		CustomerID = customerID;
	}

	public InsurancePolicies() {
		super();
	}

}
