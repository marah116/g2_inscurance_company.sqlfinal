
public class Accident_Drivers {

	private int AccidentID;
	private int DriverID;

	public int getAccidentID() {
		return AccidentID;
	}

	public void setAccidentID(int accidentID) {
		AccidentID = accidentID;
	}

	public int getDriverID() {
		return DriverID;
	}

	public void setDriverID(int driverID) {
		DriverID = driverID;
	}

	public Accident_Drivers(int accidentID, int driverID) {
		super();
		AccidentID = accidentID;
		DriverID = driverID;
	}

	public Accident_Drivers() {
		super();
	}

}
