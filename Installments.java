
public class Installments {

	private int InstallmentID;
	private double Amount;
	private String DueDate;
	private String PaidDate;
	private int PolicyID;

	public int getInstallmentID() {
		return InstallmentID;
	}

	public void setInstallmentID(int installmentID) {
		InstallmentID = installmentID;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

	public String getPaidDate() {
		return PaidDate;
	}

	public void setPaidDate(String paidDate) {
		PaidDate = paidDate;
	}

	public int getPolicyID() {
		return PolicyID;
	}

	public void setPolicyID(int policyID) {
		PolicyID = policyID;
	}

	public Installments(int installmentID, double amount, String dueDate, String paidDate, int policyID) {
		super();
		InstallmentID = installmentID;
		Amount = amount;
		DueDate = dueDate;
		PaidDate = paidDate;
		PolicyID = policyID;
	}

	public Installments() {
		super();
	}

}
