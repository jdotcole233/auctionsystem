import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public interface AuctionProjectInterface extends Remote {
    public String createAuction(AuctionItem freshAuctionItem, Integer setTime) throws RemoteException;
    public String getAuctionWinner(Integer auctionsID) throws RemoteException;
    public String placeBidforItem(Integer auctionNumber, AuctionParticipant buyer, Double offer) throws RemoteException;
    public Auction getActiveBids() throws RemoteException;
    public AuctionParticipant addAuctionParticipant(String name, String phone, String location) throws RemoteException;
    public AuctionParticipant validateExistingUser(String name, String phone) throws RemoteException;
}
