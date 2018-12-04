
import java.io.Serializable;

public class AuctionBid implements Serializable {
    private Integer itemBid;
    private  AuctionItem auctionItem;
    private AuctionParticipant  auctionBuyer;
    private Double bidOffer = 0.0;

    public AuctionBid(Integer itemBid, AuctionItem auctionItem, AuctionParticipant auctionBuyer, Double bidOffer) {
        this.itemBid = itemBid;
        this.auctionItem = auctionItem;
        this.auctionBuyer = auctionBuyer;
        this.bidOffer = bidOffer;
    }


    public Integer getItemBid() {
        return itemBid;
    }

    public void setItemBid(Integer itemBid) {
        this.itemBid = itemBid;
    }

    public AuctionParticipant getAuctionBuyer() {
        return auctionBuyer;
    }

    public void setAuctionBuyer(AuctionParticipant auctionBuyer) {
        this.auctionBuyer = auctionBuyer;
    }

    public Double getBidOffer() {
        return bidOffer;
    }

    public void setBidOffer(Double bidOffer) {
        this.bidOffer = bidOffer;
    }
}
