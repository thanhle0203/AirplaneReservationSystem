
public class Passenger {
	private String name;	//name of passenger
	private String groupName; //Group name of passenger
	
	/**
	 * Constructs Passenger class that holds a name and group of passenger
	 * @param name
	 * @param groupName
	 */
	public Passenger(String name, String groupName) {
		this.name = name;
		this.groupName = groupName;
		
	}
	
	/**
	 * Set name passenger
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set goup name of passenger
	 * @param groupName
	 */
	public void setGroup(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * Returns name of passenger
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns group name of passenger
	 * @return
	 */
	public String getGroup() {
		return groupName;
	}

}
