package customersAndDrivers;

public enum DriverState {
	OFFLINE("offline"),
	ONDUTY("onduty"),
	ONARIDE("on-a-ride"),
	OFFDUTY("offduty");
	final private String state;

	private DriverState(String state) {
		this.state = state;
	}
	

	public String getState() {
		return state;
	}
	
	

}
