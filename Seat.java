
public class Seat {
	//Create instance variable
	Passenger passenger;
	String row;
	String preferenceSeat;
	String seatNumber;
	String serviceClass;
	
	/**
	 * Constructs class seat which have row, preferenceSeat, and service class
	 * @param row
	 * @param letter
	 * @param serviceClass
	 */
	public Seat(String row, String seatNumber, String serviceClass) {
		this.passenger = null;
		this.row = row;
		this.seatNumber = seatNumber;
		this.serviceClass = serviceClass;
		
		if(serviceClass.equals("F")) {
			if(seatNumber.equals("A")||seatNumber.equals("D")) {
				preferenceSeat = "W";
			}else if (seatNumber.equals("B")||seatNumber.equals("C")) {
				preferenceSeat = "A";
			}
		}else if(serviceClass.equals("E")) {
			if(seatNumber.equals("A")||seatNumber.equals("F")){
				preferenceSeat = "W";
			} else if (seatNumber.equals("C")||seatNumber.equals("D")) {
				preferenceSeat = "A";
			}else if (seatNumber.equals("E")||seatNumber.equals("B")) {
				preferenceSeat = "C";
			}
		
		}
	}
	
	/**
	 * Return empty string row
	 * @return
	 */
	public String emptyRow() {
		row = "";
		return row;
	}
	
	/**
	 * Return empty seat number
	 * @return
	 */
	public String emptyNumber() {
		seatNumber = "";
		return seatNumber;
	}
	
	/**
	 * Return number row of seat
	 * @return
	 */
	public String getRow() {
		return row;
	}
	
	/**
	 * Return position of seat
	 * @return
	 */
	public String getSeatNumber() {
		return seatNumber;
	}
	
	/**
	 * Return seat preference 
	 * @return
	 */
	public String getPreference() {
		return preferenceSeat;
	}
	
	/**
	 * Return passenger 
	 * @return
	 */
	public Passenger getPassenger() {
		return passenger;
	}
	
	/**
	 * Assign passenger to seat 
	 * @param p
	 */
	public void assignSeat(Passenger p) {
		passenger = p;
	}

}
