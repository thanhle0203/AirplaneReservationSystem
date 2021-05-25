
public class Plane {
	
	Seat[][] first;			//two dimensional array of first class
	Seat[][] economy;		//two dimensional array of economy class
	
	private int firstW;		//instance variable number seat of first window
	private int firstA;		//instance variable number seat of first aisle
	private int economyW;	//instance variable number seat of economy window
	private int economyA;	//instance variable number seat of economy aisle
	private int economyC;	//instance variable number seat of economy center
	
	public Plane() {
		
		first = new Seat[2][4];			//Size of two dimensional array of first class
		economy = new Seat[20][6];		//Size of two dimensional array of economy class
		
		firstW = 4;						//Size of seat of first window
		firstA = 4;						//Size of seat of aisle window
		
		economyW = 40;					//Size of seat of economy window
		economyA = 40;					//Size of seat of economy aisle
		economyC = 40;					//Size of seat of economy center
		

		/*
		 * Fill in seat numbers with position aisle and window for first class
		 */
		for(int i=0; i<first.length; i++) {
			for(int j=0; j<first[0].length; j++) {
				int r = i + 1;
				String row = "" + r;
				if(j==0) {
					first[i][j] = new Seat(row, "A", "F");
				}else if(j==1) {
					first[i][j]= new Seat(row, "B", "F");
				}else if (j==2) {
					first[i][j] = new Seat(row, "C", "F");
				}else if(j==3) {
					first[i][j] = new Seat(row, "D", "F");
				}
				
			}
		}
		
		/*
		 * Fill in seat numbers with position aisle, center, and window for economy class
		 */
		for(int i=0; i<economy.length; i++) {
			for(int j=0; j<economy[0].length; j++) {
				int r = i + 10;
				String row = "" + r;
				
				if(j==0) {
					economy[i][j] = new Seat(row, "A", "E");
				}else if (j==1) {
					economy[i][j] = new Seat(row, "B", "E");
				}else if(j==2) {
					economy[i][j] = new Seat(row, "C", "E");
				}else if(j==3) {
					economy[i][j] = new Seat(row, "D", "E");
				}else if(j==4) {
					economy[i][j] = new Seat(row, "E", "E");
				}else if(j==5) {
					economy[i][j] = new Seat(row, "F", "E");
				}
		
			}
		}
	}
	
	/**
	 * Assign passenger name to first class and economy class
	 * @param row
	 * @param seatNumber
	 * @param p
	 */
	
	public void load(int row, String seatNumber, Passenger p) {
		if(row >= 10) { //row belongs to economy class
			int r = row - 10;
			if(seatNumber.equals("A")) {
				economy[r][0].assignSeat(p);
				economyW -= 1;
			}else if(seatNumber.equals("B")) {
				economy[r][1].assignSeat(p);
				economyC -= 1;
			}else if(seatNumber.equals("C")) {
				economy[r][2].assignSeat(p);
				economyA -= 1;
			}else if(seatNumber.equals("D")) {
				economy[r][3].assignSeat(p);
				economyA -= 1;
			}else if(seatNumber.equals("E")) {
				economy[r][4].assignSeat(p);
				economyC -= 1;
			}else if(seatNumber.equals("F")) {
				economy[r][5].assignSeat(p);
				economyW -= 1;
			}	
			
		}else {				//row belongs to first class
			int r = row - 1;
			if(seatNumber.equals("A")) {
				first[r][0].assignSeat(p);
				firstW -= 1;
			}else if (seatNumber.equals("B")) {
				first[r][1].assignSeat(p);
				firstA -= 1;
			}
			else if (seatNumber.equals("C")) {
				first[r][2].assignSeat(p);
				firstA -= 1;
			}
			else if (seatNumber.equals("D")) {
				first[r][3].assignSeat(p);
				firstW -= 1;
			}
			
		}
	}
	

	/**
	 * Return number of window seats occupied in first class
	 * @return
	 */
	public int getFirstW() {
		return firstW;
	}
	
	/**
	 * Return number of aisle seats occupied in first class
	 * @return
	 */
	public int getFirstA() {
		return firstA;
	}
	

	/**
	 * Return number of window seats occupied in economy class
	 * @return
	 */
	public int getEconomyW() {
		return economyW;
	}
	
	/**
	 * Return number of aisle seats occupied in economy class
	 * @return
	 */
	public int getEconomyA() {
		return economyA;
	}
	
	/**
	 * Return number of center seats occupied in economy class
	 * @return
	 */
	public int getEconomyC() {
		return economyC;
	}
	
	/**
	 * Add name, service class, position of seat number to the reservation for first class and economy class
	 * @param name - name of passenger
	 * @param serviceClass - first class or economy class
	 * @param position - Window or Aisle for first class, Window or Center or Aisle for economy class
	 */
	public void addPassenger(String name, String serviceClass, String position) {
		if(serviceClass.equals("First")) {
			outerloop:
				for(int i=0; i<first.length; i++) {
					for(int j=0; j<first[0].length; j++) {
						if(position.equals(first[i][j].getPreference())) {
							if (first[i][j].getPassenger() == null) {
								Passenger p = new Passenger(name, "");
								first[i][j].assignSeat(p);
								if(position.equals("W")) {
									firstW -= 1;
								}else {
									firstA -= 1;
								}
								break outerloop;
							}
						}
					
					}
				}
		}else if (serviceClass.equals("Economy")) {
			outerloop:
				for(int i=0; i<economy.length; i++) {
					for (int j=0; j<economy[0].length; j++) {
						
						if(position.equals(economy[i][j].getPreference())) {
							if(economy[i][j].getPassenger()==null) {
								Passenger p = new Passenger(name, "");
								economy[i][j].assignSeat(p);
				
								if(position.equals("W")) {
									economyW -= 1;
								}else if (position.equals("A")) {
									economyA -= 1;
								}else {
									economyC -= 1;
								}
								
								break outerloop;
							}
							
							
						}					
					
					}
				}
		}
	}

	/**
	 * Add name, service class, and group name to the reservation for first class and economy class
	 * @param name - name of each passenger in the group name
	 * @param serviceClass - First class or Economy class
	 * @param groupName - group 
	 */
	@SuppressWarnings("unused")
	public void addGroupPassenger(String name, String serviceClass, String groupName) {
		if(serviceClass.equals("First")) {
			outerloop:
				for(int i=0; i<first.length; i++) {
					for(int j=0; j<first[0].length; j++) {
						
							if (first[i][j].getPassenger() == null) {
								Passenger p = new Passenger(name, groupName);
								first[i][j].assignSeat(p);
									firstW -= 1;					
									firstA -= 1;
									break outerloop;
								}
														
						}	
					}
				
		}else if (serviceClass.equals("Economy")) {
			outerloop:
				for(int i=0; i<economy.length; i++) {
					for (int j=0; j<economy[0].length; j++) {
		
							if(economy[i][j].getPassenger()==null) {
								Passenger p = new Passenger(name, groupName);
								economy[i][j].assignSeat(p);						
									economyW -= 1;				
									economyA -= 1;
									economyC -= 1;	
									break outerloop;
							}				
						
						}					
					
					}
		}
	}
	
	/**
	 * Cancels reservation for name of first class or economy class
	 * @param name - name passenger they want to cancel
	 * @param serviceClass - first class or economy class 
	 */
	public void cancelIndividual(String name, String serviceClass) {
		boolean found = false;
		if(serviceClass.equalsIgnoreCase("First")) {		
				for(int i=0; i<first.length; i++) {
					for(int j=0; j<first[0].length; j++) {
						if (first[i][j].getPassenger()!= null) {
							if(first[i][j].getPassenger().getName().equals(name)) {
								Passenger p = new Passenger("", "");
					
								first[i][j].assignSeat(p);
								first[i][j].emptyRow();
								first[i][j].emptyNumber();
								firstW += 1;					
								firstA += 1;
								
								found = true;
							}									
						}	
					
					}	
				}
				
				if(found) {
					System.out.println("\nSuccessful canceled named " + name + " from the First Class' Seat");
				}else {
					System.out.println("\nNot found " + name + " name\n");
				}
				
		}else if (serviceClass.equalsIgnoreCase("Economy")) {
				for(int i=0; i<economy.length; i++) {
					for (int j=0; j<economy[0].length; j++) {
						if(economy[i][j].getPassenger() !=null) {
							if(economy[i][j].getPassenger().getName().equals(name)) {
								Passenger p = new Passenger("", "");
								
								economy[i][j].assignSeat(p);
								economy[i][j].emptyRow();
								economy[i][j].emptyNumber();
								economyW += 1;				
								economyA += 1;
								economyC += 1;	
								
								found = true;
							}						
					  }	
										
					}								
				}
				
				if(found) {
					System.out.println("\nSuccessful canceled named " + name + " from the First Economy' Seat\n");
				}else {
					System.out.println("\nNot found " + name + " name\n");
				}
		}
	}
	
	/**
	 * Cancel group name of the reservation
	 * @param serviceClass
	 * @param groupName
	 */
	public void cancelGroupName(String serviceClass, String groupName) {
		boolean found = false;
		if(serviceClass.equals("First")) {
			for(int i=0; i<first.length; i++) {
				for(int j=0; j<first[0].length; j++) {
					if (first[i][j].getPassenger() != null){
						if(first[i][j].getPassenger().getGroup().equals(groupName)) {
							Passenger p = new Passenger("", "");
							
							first[i][j].assignSeat(p);
							first[i][j].emptyRow();
							first[i][j].emptyNumber();
							firstW += 1;					
							firstA += 1;
							
							found = true;
						}
					}
											
				}	
			}
			
			if(found) {
				System.out.println("\nSuccessful canceled group named " + groupName + " from the First Class' Seat\n");
			}else {
				System.out.println("\nNot found " + groupName + " group name\n");
			}
	
		}else if (serviceClass.equals("Economy")) {
			for(int i=0; i<economy.length; i++) {
				for (int j=0; j<economy[0].length; j++) {
					if(economy[i][j].getPassenger()!=null) {
						if(economy[i][j].getPassenger().getGroup().equals(groupName)) {
							Passenger p = new Passenger("", "");
							
							economy[i][j].assignSeat(p);
							economy[i][j].emptyRow();
							economy[i][j].emptyNumber();
							economyW += 1;				
							economyA += 1;
							economyC += 1;	
							
							found = true;
						}
						
					}		
									
				}						
			}
			
			if(found) {
				System.out.println("\nSuccessful canceled group named " + groupName + " from the Ecnonomy Class' Seat\n");
			}else {
				System.out.println("\nNot found " + groupName + " name\n");
			}
		}
	}
	
	
	/**
	 * Return a manifest list of seat number and name passenger for first class
	 * @return chart of seat reservation
	 */
	public String getManifestFirst() {
		String chart = "";
		for(int i=0; i<first.length; i++) {
			for(int j=0; j<first[0].length; j++) {
				if(first[i][j]!=null && first[i][j].getPassenger()!= null) {
					Seat seat = first[i][j];
					chart += seat.getRow();
					chart += seat.getSeatNumber() + ": ";
					chart += seat.getPassenger().getName();
					chart += "\n";
				}
			
			}
		}
		return chart;
	}
	
	
	/**
	 * Return a manifest list of seat number and name passenger for Economy class
	 * @return chart 
	 */
	public String getManifestEconomy() {
		String chart = "";
		for (int i=0; i<economy.length; i++) {
			for (int j=0; j<economy[0].length; j++) {
				if(economy[i][j]!=null && economy[i][j].getPassenger()!=null) {
					Seat seat = economy[i][j];
					chart += seat.getRow();
					chart += seat.getSeatNumber() + ": ";
					chart += seat.getPassenger().getName();
					chart += "\n";
				}
				
			}
		}
		return chart;
	}
	

	/**
	 * Return a manifest list of seat number and group name passengers for first class
	 * @return chart
	 */
	public String getManifestGroupNameFirst() {
		String chart = "";
		
		for(int i=0; i<first.length; i++) {
			for(int j=0; j<first[0].length; j++) {
				if(first[i][j]!=null && first[i][j].getPassenger()!= null) {
					Seat seat = first[i][j];
					chart += seat.getRow();
					chart += seat.getSeatNumber() + ": ";
					chart += seat.getPassenger().getName();
					chart += "\n";
				}
			
			}
		}
		return chart;
	}
	
	/**
	 * Return a manifest list of seat number and group name passengers for economy class
	 * @return chart
	 */
	public String getManifestGroupNameEconomy() {
		String chart = "";
		for (int i=0; i<economy.length; i++) {
			for (int j=0; j<economy[0].length; j++) {
				if(economy[i][j]!=null && economy[i][j].getPassenger()!=null) {
					Seat seat = economy[i][j];
					chart += seat.getRow();
					chart += seat.getSeatNumber() + ": ";
					chart += seat.getPassenger().getName();
					chart += "\n";
				}
				
			}
		}
		return chart;
	}
	
	/**
	 * Returns an availability list of seat number for first class
	 * @return chart
	 */
	public String getAvailabilityFirst() {
		
		String chart = "\nFirst Class\n";
		for (int i=0; i<first.length; i++) {
			chart +=  first[i][0].getRow() + ": ";
			for(int j=0; j<first[0].length; j++) {
				if(first[i][j].getPassenger() == null || first[i][j].getPassenger().equals("")) {
					Seat seat = first[i][j];
					chart += seat.getSeatNumber() + ", ";	
				}
			}
			chart = chart.substring(0, (chart.length() - 2));
			chart += "\n";
		 
		}
		return chart;
	}

	/**
	 * Returns an availability list of seat number for economy class
	 * @return
	 */
	public String getAvailabilityEconomy() {
		String chart = "\nEconomy Class\n";
		
		for(int i=0; i<economy.length; i++) {
			chart += economy[i][0].getRow() + ": ";
			for(int j=0; j<economy[0].length; j++) {
				if(economy[i][j].getPassenger() == null) {
					Seat seat = economy[i][j];
					chart += seat.getSeatNumber() + ", ";
				}
				
			}
			chart = chart.substring(0, chart.length() - 2);
			chart += "\n";
		}
		return chart;
	}
	

	
	
	/**
	 * Return list of seat numbers, name of passenger in first class and economy class
	 * @return list
	 */
	public String toFile() {
		String list = "";
		for(int i=0; i<first.length; i++) {
			for (int j=0; j<first[0].length; j++) {
				if(first[i][j].getPassenger()!=null) {
					Seat seat = first[i][j];
					list += seat.getRow() + seat.getSeatNumber() + ", ";
					if(seat.getPassenger().getGroup()!="") {
						list += "G, " + seat.getPassenger().getGroup() + ", " + seat.getPassenger().getName();
						list += "\n";
					}else {
						list += "I, " + seat.getPassenger().getName();
						list += "\n";
					}
				}
			}
		}
	
		for(int i=0; i<economy.length; i++) {
			for (int j=0; j<economy[0].length; j++) {
				if (economy[i][j].getPassenger()!=null) {
					Seat seat = economy[i][j];
					list += seat.getRow() + seat.getSeatNumber() + ", ";
					if (seat.getPassenger().getGroup()!="") {
						list += "G, " + seat.getPassenger().getGroup() + ", " + seat.getPassenger().getName();
						list += "\n";
					}else {
						list += "I, " + seat.getPassenger().getName();
						list += "\n";
					}
				}
			}
		}
		
		return list;
		
	}


}
