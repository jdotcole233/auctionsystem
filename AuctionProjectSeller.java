import java.rmi.Naming;
import java.util.Scanner;
import java.io.*;

public class AuctionProjectSeller {

    public static void main(String[] args) throws Exception {
        AuctionProjectInterface seller = (AuctionProjectInterface) Naming.lookup("AuctionBind");
        Scanner keyboardInput = new Scanner(System.in);

        Integer choice = null;
        AuctionItem item = null;
        AuctionParticipant sellerParticipant = null;
        int repeatOne = 0,repeatTwo = 0;
        do {
            System.out.println("Welcome to CX auction center..");
            System.out.println("1. Enter Name and Number as returning customer");
            System.out.println("2. Register as a seller\n3. Exit");

            choice = keyboardInput.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter your name");
                    String yourName = keyboardInput.next();
                    System.out.println("Enter your phone number");
                    String phonenumber = keyboardInput.next();
                    sellerParticipant = seller.validateExistingUser(yourName, phonenumber);
                    if (sellerParticipant == null) {
                        System.out.println("Something went wrong");
                    }else{
                        repeatOne = 1;
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
                    sellerParticipant = seller.addAuctionParticipant(name,phone,location);
                    if (sellerParticipant == null){
                        System.out.println("Something went wrong");
                    } else {
                        System.out.println("You are ready " + sellerParticipant.getParticipant_name());
                        repeatOne = 1;
                    }

                    break;
                case 3:
                    System.out.println("Good Bye!!");
                    repeatOne = 1;
                default:
                    System.out.println("Invalid input");
            }

        }while(repeatOne == 0);

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();


        do{
            System.out.println("\n\nWelcome " + sellerParticipant.getParticipant_name());
            System.out.println("What will you like to do ?");
            System.out.println("1. Create an Auction\n2. View winner of Auction\n3. Exit");
            choice = keyboardInput.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("Fill out the following information to add new Auction");
                    System.out.println("Enter Item name:");
                    String itemName = keyboardInput.next();
                    System.out.println("Enter starting price for item:");
                    Double starting_price = keyboardInput.nextDouble();
                    System.out.println("Enter reserved price:");
                    Double reserved_price = keyboardInput.nextDouble();
                    item = new AuctionItem(itemName, starting_price, reserved_price, sellerParticipant);
                    System.out.println("You are expected to set a time for how long this Auctions lasts");
                    System.out.println("Enter a time (integer values only)");
                    Integer setTime = keyboardInput.nextInt();
                    seller.createAuction(item, setTime);
                    break;
                case 2:
                    System.out.println("Enter auction ID to check the winner");
                    Integer auctionID = keyboardInput.nextInt();
                    System.out.println(seller.getAuctionWinner(auctionID));
                    break;
                case 3:
                    System.out.println("Thank you.. Bye");
                    repeatTwo = 1;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (repeatTwo == 0);
    }
}
