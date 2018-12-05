import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class AuctionProjectInterfaceImplementation extends UnicastRemoteObject implements AuctionProjectInterface {

    Auction auctions = null;
    AuctionBid someBidPlaced = null;
    //Timer auctionTimer;

    public  AuctionProjectInterfaceImplementation() throws Exception {
        super();
        auctions = new Auction();
    }

    @Override
    public String createAuction(AuctionItem freshAuctionItem, Integer setTime) throws RemoteException {
          if(freshAuctionItem == null){
              return "You can't create an Empty Auction";
          }
          Auction ab = new Auction();
          Timer  auctionTimer = new Timer();
          TimerTask  auctionTask = new TimerTask(){
              @Override
              public void run() {
                  //auctions.getAuctionCreated().remove();
                  //try{Thread.sleep(3000);}catch(Exception e){}
                  System.out.println(Thread.currentThread().getName());
                  for (Map.Entry<Integer, AuctionItem> a : ab.getAuctionCreated().entrySet()){
                      if (a.getKey() == ab.getAuctionID()){
                        System.out.println("Auction Ended for " + ab.getAuctionID() + " For item  " + ab.getAuctionCreated().get(ab.getAuctionID()).getItem_name());
                        ab.getAuctionCreated().get(ab.getAuctionID()).setStatus("closed");
                        auctionTimer.cancel();
                        break;
                      }
                  }
              }
          };
          try{ab.createNewAuction(freshAuctionItem);}catch(Exception e){}
          try{auctions.createNewAuction(freshAuctionItem);}catch(Exception e){}


          auctionTimer.schedule(auctionTask,(setTime * 60000));
        return "Auction created successfully";
    }

    @Override
    public String getAuctionWinner(Integer auctionsID) throws RemoteException {
        String winnerReturns = "";
           for(Map.Entry<Integer, AuctionBid> winner : auctions.getTrackBidding().entrySet()) {
               if (winner.getKey().equals(auctionsID)) {
                   AuctionItem valid = auctions.getAuctionCreated().get(auctionsID);
                   if (valid.getItem_reserved_price() > winner.getValue().getBidOffer()){
                       winnerReturns = "Reserved price of  = {" + valid.getItem_reserved_price() + " not reached\n\n";
                       winnerReturns += "Buyer :" + winner.getValue().getAuctionBuyer().getParticipant_name() +"\n";
                       winnerReturns += "Made an offer of :" + winner.getValue().getBidOffer() + "\n";
                       winnerReturns += "For item :" + auctions.getAuctionCreated().get(winner.getValue().getItemBid()).getItem_name() + "\n";
                       return winnerReturns;
                   }
                   winnerReturns = "Buyer :" + winner.getValue().getAuctionBuyer().getParticipant_name() +"\n";
                   winnerReturns += "Made an offer of :" + winner.getValue().getBidOffer() + "\n";
                   winnerReturns += "For item :" + auctions.getAuctionCreated().get(winner.getValue().getItemBid()) + "\n";
                   return winnerReturns;
               }
           }
           return "";
    }

    @Override
    public String placeBidforItem(Integer auctionNumber, AuctionParticipant buyer, Double offer) throws RemoteException {

        if(auctions.getAuctionCreated().containsKey(auctionNumber)){
            AuctionItem checkItemPrice = auctions.getAuctionCreated().get(auctionNumber);
            someBidPlaced = new AuctionBid(auctionNumber, checkItemPrice, buyer, offer);
            if (offer > checkItemPrice.getItem_starting_price()){
              System.out.println(auctions.getAuctionCreated().containsKey(auctionNumber));
                if(auctions.getTrackBidding().size() > 0 ){
                     AuctionBid bidded  = auctions.getTrackBidding().get(auctionNumber);
                    if (bidded != null ){
                       if (someBidPlaced != null){
                           if(offer > bidded.getBidOffer()){
                               AuctionItem biddedItem = auctions.getAuctionCreated().get(auctionNumber);
                               someBidPlaced = new AuctionBid(auctionNumber, biddedItem, buyer, offer);
                               auctions.trackBid(auctionNumber, someBidPlaced);
                               return "Bid of :" + offer + " has been placed on Item : " + biddedItem.getItem_name();
                             } else {
                               return "Your offer " + offer +" is to small for the current bid";
                           }
                       }
                  } else {
                    auctions.trackBid(auctionNumber, someBidPlaced);
                  }
                }else {
                    auctions.trackBid(auctionNumber, someBidPlaced);
                    return "You placed the first Bid";
                }
            }
        }
        return  "Auction Number :" + auctionNumber + " Not found";
    }

    @Override
    public Auction getActiveBids() throws RemoteException {
        return auctions;
    }

    @Override
    public AuctionParticipant addAuctionParticipant(String name, String phone, String location) throws RemoteException {
        AuctionParticipant participant = new AuctionParticipant(name, phone, location);
        if (auctions.getAuctionParticipants().isEmpty()){
            auctions.setAuctionParticipants(participant);
            return participant;
        }

        for(AuctionParticipant participantValidate : auctions.getAuctionParticipants()){
            if (participantValidate.getParticipant_name().equals(name)){
                return participantValidate;
            }
        }

        auctions.setAuctionParticipants(participant);
        return  participant;
    }


    @Override
    public AuctionParticipant validateExistingUser(String name, String phone) throws RemoteException {
        if (name.isEmpty() && phone.isEmpty()){
            return  null;
        }
        for(AuctionParticipant participantValidate : auctions.getAuctionParticipants()){
            if (participantValidate.getParticipant_name().equals(name) && participantValidate.getParticipant_number().equals(phone)){
                return participantValidate;
            }
        }
        return null;
    }
}
