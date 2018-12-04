import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.Vector;

public class Auction implements Serializable {

    private ArrayList<AuctionParticipant> auctionParticipants = null;
    private HashMap<Integer, AuctionItem> auctionCreated = null;
    private HashMap<Integer, Date> auctionTimeBound = null;
    private HashMap<Integer, AuctionBid> trackBidding = null;
    private Integer auctionID = 0;

    @SuppressWarnings("unchecked")
    public Auction() {
        auctionParticipants = new ArrayList<>();
        auctionCreated = new HashMap<>();
        trackBidding = new HashMap<>();
    }

    public void registerParticipants(String name, String phone_number, String location){
        AuctionParticipant participant = new AuctionParticipant(name, phone_number, location);
        if (auctionParticipants.isEmpty()){
            auctionParticipants.add(participant);
            System.out.println("You are ready now");
            return;
        }

        for(AuctionParticipant participantValidate : auctionParticipants){
            if (participantValidate.getParticipant_name().equals(name)){
                System.out.println("We already have you in our system");
                return;
            }
        }

        auctionParticipants.add(participant);
        System.out.println("You are ready now");
    }



    public void trackBid(Integer auctionID, AuctionBid bid){
        trackBidding.put(auctionID, bid);
    }

    public HashMap<Integer, AuctionBid> getTrackBidding(){
        return trackBidding;
    }


    public ArrayList<AuctionParticipant> getAllParticipants(){
        return auctionParticipants;
    }

    public void createNewAuction(AuctionItem item) throws Exception {
        auctionID++;
        if(auctionCreated.isEmpty()){
            auctionCreated.put(auctionID, item);
//                auctionTimeBound.put(auctionID, date);
            System.out.println("Auction created successfully");
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            getAuctionList();
            return;
        }

        auctionCreated.put(auctionID,item);
      //  new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        getAuctionList();
//            auctionTimeBound.put(auctionID, date);

    }

    public void getAuctionList(){
        String tabularStructure = "| %-9d | %-10s| %-14.2f | %-15s |%n";
        System.out.println("Auction Table");
        System.out.format("%n|--------------------------------------------------------|%n");
        System.out.format("| AuctionID | Item Name | Starting Price | Seller name |%n");
        System.out.format("|--------------------------------------------------------|%n");
        for(Map.Entry<Integer, AuctionItem> single: auctionCreated.entrySet()){
          if (!single.getValue().getStatus().contains("closed")){
                System.out.format(tabularStructure,single.getKey(), single.getValue().getItem_name(), single.getValue().getItem_starting_price(),single.getValue().getSeller().getParticipant_name());
                System.out.format("|--------------------------------------------------------|%n");
            }
        }
        // System.out.format("|--------------------------------------------------------|%n");
    }

    public Vector getCreatedAuctions(){
        Vector  auctionList = new Vector();

        if (auctionCreated.isEmpty() && auctionTimeBound.isEmpty()){
            System.out.println("No auctions at this time");
            System.out.println("Check back later");
        }
        auctionList.add(auctionCreated);
        auctionList.add(auctionTimeBound);
        return auctionList;
    }

    public void setAuctionParticipants(AuctionParticipant auctionParticipants) {
        this.auctionParticipants.add(auctionParticipants);
    }

    public ArrayList<AuctionParticipant> getAuctionParticipants() {
        return auctionParticipants;
    }

    public HashMap<Integer, AuctionItem> getAuctionCreated() {
        return auctionCreated;
    }

    public Integer getAuctionID() {
     return auctionID;
   }
}
