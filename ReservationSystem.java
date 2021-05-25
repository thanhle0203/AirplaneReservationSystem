
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReservationSystem {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException{
		Plane plane = new Plane();		//Create new object of class Plane
		String group = null;
		
		File file = new File(args[0]);		//Create arguments of file object
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	//Get input from scanner object
		sc.useDelimiter("\\n");
		
		/*
		 * Create new input file if the file doens't exit
		 */
		if(!file.exists()) {
			file.createNewFile();
			boolean quit = false;
			
			while(!quit) {
				try {
					//Display Main Menu of the reservation
					System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservation, Print Seating "
							+ "[A]vailability Chart, Print [M]anifest, [Q]uit");
					String input = sc.nextLine();
					
					
					if(input.equalsIgnoreCase("P")) {
						System.out.print("Name: ");
						String name = sc.nextLine();
						
						System.out.print("Service Class (First or Economy): ");
						String service_class = sc.nextLine();
						
						if(service_class.equalsIgnoreCase("First")) {
							System.out.print("Seat Preference (W or A): ");
							String preference = sc.nextLine();
							if(preference.equals("W") && plane.getFirstW()<1) {
								System.out.println("No Window seat available. Please select a different service class or preference");
							}else if(preference.equals("A") && plane.getFirstA()<1) {
								System.out.println("No Aisle seat available. Please select a different service class or preference");
							}else {
								System.out.println("\nSuccessful added " + name + " to the first class's seat!\n");
								plane.addPassenger(name, service_class, preference);
								System.out.println(plane.getManifestFirst());							
							}
						} else if (service_class.equalsIgnoreCase("Economy")){
							System.out.print("Seat Preference (W or A or C): ");
							String preference = sc.nextLine();
							if(preference.equals("A") && plane.getEconomyA()<1) {
								System.out.println("No Aisle seat available. Please select a different service class or preference");
							}else if(preference.equals("C") && plane.getEconomyC()<1) {
								System.out.println("No Center seat available. Please select a different service class or preference");
							}else {
								System.out.println("\nSuccessful added " + name + " to the economy class's seat!\n");
								plane.addPassenger(name, service_class, preference);
								System.out.println(plane.getManifestEconomy());
							}
						}
																		
						
					} else if(input.equalsIgnoreCase("G")) {
						System.out.print("Group name: ");
						String gname = sc.nextLine().toString(); //name 
						
						String group_name;
						
						while(true) {
							System.out.print("Names: ");
							//Scanner g = new Scanner(System.in);
							group_name = sc.nextLine();	//group of people
							
							if(group_name.endsWith(", ")) {
								System.out.println("Please provide names seperated by ,.");
								continue;
							}
							if(!group_name.contains(", ")) {
								System.out.println("Please seperate names by ,.");
								continue;
							}
							break;
						}
						
						String gservice;
						
						
						while(true) {
							System.out.print("Service Class (Economy, First): ");
							gservice = sc.nextLine();
							
							if(gservice.equalsIgnoreCase("first")) {
								//add group to first class
								if(true) {
									System.out.println("Group " + gname + " has been added");
									String[] arr = group_name.split(", ");
									for(String a :arr) {
										a.trim();
										plane.addPassenger(a, gservice, "");
										plane.addGroupPassenger(a, gservice, gname);
										
									}
									System.out.println("\nGroupName: " + gname + "\n" + plane.getManifestGroupNameFirst());
								} else {
									System.out.println("Not enough seat to add all group members");
								}
								break;
							}else if(gservice.equalsIgnoreCase("economy")) {
								//add group to economy class
								if(true) {
							
									System.out.println("Group " + gname + " has been added!");
									String[] arr = group_name.split(", ");
									for(String a :arr) {
										a.trim();
										plane.addGroupPassenger(a, gservice, gname);
										
									}
									System.out.println("\nGroupName: " + gname + "\n" +plane.getManifestGroupNameEconomy());
								} else {
								System.out.println("Not enough seats to add group.");
							    }
							    break;
							
						  }else {
							  System.out.println("Invalid input. Please type first or economy");
							  continue;
						  }
						}
				
					}else if(input.equalsIgnoreCase("C")) {
						System.out.print("[I]dividual or [G]roup? ");
						String inName = sc.nextLine();
						if(inName.equalsIgnoreCase("I")) {
							System.out.print("Service Class (First or Economy): ");
							String inService = sc.nextLine();
							
							System.out.print("Enter name of passenger: ");
							String name = sc.nextLine();
							
							if(inService.equalsIgnoreCase("First")) {
								plane.cancelIndividual(name, inService);
								System.out.println(plane.getManifestFirst());
							}else if(inService.equalsIgnoreCase("Economy")){
								plane.cancelIndividual(name, inService);
								System.out.println(plane.getManifestEconomy());
							}
							
						}else if(inName.equalsIgnoreCase("G")) {
							System.out.print("Service Class (First or Economy): ");
							String inService = sc.nextLine();
							
							System.out.print("Enter group name: ");
							String gname = sc.nextLine();
							
							if(inService.equalsIgnoreCase("First")) {
								plane.cancelGroupName(inService, gname);
								//System.out.println(gname + " has been canceled.");
							}else if(inService.equalsIgnoreCase("Economy")){
								plane.cancelGroupName(inService, gname);
								//System.out.println(gname + " has been canceled.");
							}
						}
								
					}else if(input.equalsIgnoreCase("A")) {
						try {
							System.out.print("Service Class (First or Economy)? ");
							String in = sc.nextLine();
							if(in.equalsIgnoreCase("First")) {
								System.out.println(plane.getAvailabilityFirst());
							}else if(in.equalsIgnoreCase("Economy")){
								System.out.println(plane.getAvailabilityEconomy());
					
							}
						}catch(IllegalArgumentException e) {
							System.out.println(e.getMessage());
						}
						
					}
					else if(input.equalsIgnoreCase("M")) {
						System.out.print("Service Class (First or Economy)? ");
						String in = sc.nextLine();
						if(in.equalsIgnoreCase("First")){
							System.out.println(plane.getManifestFirst());
						}else if (in.equalsIgnoreCase("Economy")) {
							System.out.println(plane.getManifestEconomy());
						}
							
								
					}
					else if(input.equalsIgnoreCase("Q")) {
						PrintWriter out = new PrintWriter(args[0]);
						out.println(plane.toFile());
						out.close();
						quit = true;
					}
					
				}catch(IOException exp) {
					System.out.println("invalid input " + exp.getMessage());
				}
				
			}
		} else {
			Scanner inputFile = new Scanner(file);
			//Assume a file is already created
			//TODO
			
		//	sc.nextLine(); //skip the first line
			while(inputFile.hasNextLine()) {
				String lineString = inputFile.nextLine();
				//Get rid of commas
				String[] arr = lineString.split(", ");
				for(String a :arr) {
					a.trim();
				}
				
				if(arr.length == 3) {
					group = "";
					Passenger passenger = new Passenger(arr[2], group);
					if(arr[0].length()==2) {//1A, 2A
						String seat = arr[0].substring(0,1);
						plane.load(Integer.parseInt(seat), arr[0].substring(1,2), passenger);
					}else {
						String seat = arr[0].substring(0,2);
						plane.load(Integer.parseInt(seat), arr[0].substring(2,3), passenger);
					}
								
				}else if(arr.length == 4) {
					group = arr[2];
					Passenger passenger = new Passenger(arr[3], group);
					if(arr[0].length()==2) {//1A, 2A
						String seat = arr[0].substring(0,1);
						plane.load(Integer.parseInt(seat), arr[0].substring(1,2), passenger);
					}else {
						String seat = arr[0].substring(0,2);
						plane.load(Integer.parseInt(seat), arr[0].substring(2,3), passenger);
					}
				}
			}
			inputFile.close();
			boolean quit = false;
			
			while(!quit) {
				try {
					//Display Main Menu of the reservation
					System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservation, Print Seating "
							+ "[A]vailability Chart, Print [M]anifest, [Q]uit");
					String input = sc.nextLine();
					
					if(input.equalsIgnoreCase("P")) {
						System.out.print("Name: ");
						String name = sc.nextLine();
						
						System.out.print("Service Class (First or Economy): ");
						String service_class = sc.nextLine();
						
						if(service_class.equalsIgnoreCase("First")) {
							System.out.print("Seat Preference (W or A): ");
							String preference = sc.nextLine();
							if(preference.equals("W") && plane.getFirstW()<1) {
								System.out.println("No Window seat available. Please select a different service class or preference");
							}else if(preference.equals("A") && plane.getFirstA()<1) {
								System.out.println("No Aisle seat available. Please select a different service class or preference");
							}else {
								System.out.println("\nSuccessful added " + name + " to the first class's seat!\n");
								plane.addPassenger(name, service_class, preference);
								System.out.println(plane.getManifestFirst());							
							}
						} else if (service_class.equalsIgnoreCase("Economy")){
							System.out.print("Seat Preference (W or A or C): ");
							String preference = sc.nextLine();
							if(preference.equals("A") && plane.getEconomyA()<1) {
								System.out.println("No Aisle seat available. Please select a different service class or preference");
							}else if(preference.equals("C") && plane.getEconomyC()<1) {
								System.out.println("No Center seat available. Please select a different service class or preference");
							}else {
								System.out.println("\nSuccessful added " + name + " to the economy class's seat!");
								plane.addPassenger(name, service_class, preference);
								System.out.println(plane.getManifestEconomy());
							}
						}
																		
						
					} else if(input.equalsIgnoreCase("G")) {
						System.out.print("Group name: ");
						//Scanner gn = new Scanner(System.in);
						String gname = sc.nextLine().toString(); //name 
						
						String group_name;
						
						while(true) {
							System.out.print("Names: ");
							group_name = sc.nextLine();	//group of people
							
							if(group_name.endsWith(", ")) {
								System.out.println("Please provide names seperated by ,.");
								continue;
							}
							if(!group_name.contains(", ")) {
								System.out.println("Please seperate names by ,.");
								continue;
							}
							break;
						}
						
						String gservice;
						
						
						while(true) {
							System.out.print("Service Class (Economy, First): ");
							gservice = sc.nextLine();
							
							if(gservice.equalsIgnoreCase("first")) {
								//add group to first class
								if(true) {
									System.out.println("Group " + gname + " has been added");
									String[] arr = group_name.split(", ");
									for(String a :arr) {
										a.trim();
										plane.addPassenger(a, gservice, "");
										plane.addGroupPassenger(a, gservice, gname);
										
									}
									System.out.println("\nGroup Name: " + gname + "\n" + plane.getManifestGroupNameFirst());
								} else {
									System.out.println("Not enough seat to add all group members");
								}
								break;
							}else if(gservice.equalsIgnoreCase("economy")) {
								//add group to economy class
								if(true) {
							
									System.out.println("Group " + gname + " has been added!");
									String[] arr = group_name.split(", ");
									for(String a :arr) {
										a.trim();
										plane.addGroupPassenger(a, gservice, gname);
										
									}
									System.out.println("\nGroup Name: " + gname + "\n" +plane.getManifestGroupNameEconomy());
								} else {
								System.out.println("Not enough seats to add group.");
							    }
							    break;
							
						  }else {
							  System.out.println("Invalid input. Please type first or economy");
							  continue;
						  }
						}
				
					}else if(input.equalsIgnoreCase("C")) {
						System.out.print("[I]dividual or [G]roup? ");
						String inName = sc.nextLine();
						if(inName.equalsIgnoreCase("I")) {
							System.out.print("Service Class (First or Economy): ");
							String inService = sc.nextLine();
							
							System.out.print("Enter name of passenger: ");
							String name = sc.nextLine();
							
							if(inService.equalsIgnoreCase("First")) {
								plane.cancelIndividual(name, inService);					
								System.out.println(plane.getManifestFirst());
							}else if(inService.equalsIgnoreCase("Economy")){
								plane.cancelIndividual(name, inService);
								System.out.println(plane.getManifestEconomy());
							}
							
						}else if(inName.equalsIgnoreCase("G")) {
							System.out.print("Service Class (First or Economy): ");
							String inService = sc.nextLine();
							
							System.out.print("Enter group name: ");
							String gname = sc.nextLine();
							
							if(inService.equalsIgnoreCase("First")) {
								plane.cancelGroupName(inService, gname);
							}else if(inService.equalsIgnoreCase("Economy")){
								plane.cancelGroupName(inService, gname);
							}
						}
								
					}else if(input.equalsIgnoreCase("A")) {
						System.out.print("Service Class (First or Economy)? ");
						String in = sc.nextLine();
						if(in.equalsIgnoreCase("First")) {
							System.out.println(plane.getAvailabilityFirst());
						}else if(in.equalsIgnoreCase("Economy")){
							System.out.println(plane.getAvailabilityEconomy());
				
						}
					}
					else if(input.equalsIgnoreCase("M")) {
						System.out.print("Service Class (First or Economy)? ");
						String in = sc.nextLine();
						if(in.equalsIgnoreCase("First")){
							System.out.println(plane.getManifestFirst());
						}else if (in.equalsIgnoreCase("Economy")) {
							System.out.println(plane.getManifestEconomy());
						}
					}
					else if(input.equalsIgnoreCase("Q")) {
						PrintWriter out = new PrintWriter(args[0]);
						out.println(plane.toFile());
						out.close();
						quit = true;
					}
					
				}catch(IOException exp) {
					System.out.println("invalid input " + exp.getMessage());
				}
				
			}	
			
			}
		}
	
}
