
public class Branch {
	private int BranchID;
	private String BranchName;
	private int SiteID;

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public int getSiteID() {
		return SiteID;
	}

	public void setSiteID(int siteID) {
		SiteID = siteID;
	}

	public Branch(int branchID, String branchName, int siteID) {
		super();
		BranchID = branchID;
		BranchName = branchName;
		SiteID = siteID;
	}

	public Branch() {
		super();
	}

	public Branch(int branchID) {
		super();
		BranchID = branchID;
	}

}
