import java.rmi.Naming;
import java.util.Scanner;


public class AuctionProjectBuyer {
  public static void main (String [] args) throws Exception {
    AuctionProjectInterface auctionBuyer = (AuctionProjectInterface) Naming.lookup("AuctionBind");
    Scanner keyboardInput = new Scanner(System.in);
    Integer choice;
    AuctionParticipant validateParticipant = null;
    Integer repeatOne = 0, repeatTwo = 0;

    do {
    System.out.println("Welcome to CX auction center..");
    System.out.println("1. Enter Name and Number as returning customer");
    System.out.println("2. Register as a Buyer");
    System.out.println("3. Exit");

    choice = keyboardInput.nextInt();

    switch (choice){
      case 1:
          System.out.println("Enter your name");
          String yourName = keyboardInput.next();
          System.out.println("Enter your phone number");
          String phonenumber = keyboardInput.next();
          validateParticipant = auctionBuyer.validateExistingUser(yourName, phonenumber);
          if (validateParticipant == null) {
              System.out.println("Something went wrong");
          }

          break;
      case 2:
      String name, phone, location;
      System.out.println("Enter name: ");
      name = keyboardInput.next();
      System.out.println("Enter phone: ");
      phone = keyboardInput.next();
      System.out.println("Enter location: ");
      location = keyboardInput.next();
      validateParticipant = auctionBuyer.addAuctionParticipant(name,phone,location);
      if (validateParticipant == null){
         System.out.println("Something went wrong");
      } else {
        System.out.println("You are ready " + validateParticipant.getParticipant_name());
        repeatOne = 1;
      }
       break;
      default:
          System.out.println("Invalid input");
    }
  } while (repeatOne == 0);

  new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();


  do{
    System.out.println("\n\nWelcome Buyer { " + validateParticipant.getParticipant_name() + " }");
    System.out.println("What will you like to do ?");
    System.out.println("1. View available Auctions\n2. Place a bid\n3. Exit");
    choice = keyboardInput.nextInt();

    switch(choice) {
      case 1:
          auctionBuyer.getActiveBids().getAuctionList();
          break;
      case 2:
          System.out.println("Choose an item by selecting an Auction ID");
          System.out.println("Select an Auction ID");
          Integer auctionID = keyboardInput.nextInt();
          System.out.println("Make an offer {Should be higher than previous offer}");
          Double buyerOffer = keyboardInput.nextDouble();
          System.out.println(auctionBuyer.placeBidforItem(auctionID, validateParticipant,buyerOffer));
          break;
      case 3:
          System.out.println("Thank you.. Bye");
          break;
      default:
          System.out.println("Invalid input");
    }
  } while (repeatTwo == 0);
  }
}
