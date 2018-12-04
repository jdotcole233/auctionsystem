
import java.io.Serializable;

public class AuctionItem implements Serializable {
    private String item_name;
    private Double item_starting_price;
    private Double item_reserved_price;
    private AuctionParticipant seller;
    private String status = "";

    public AuctionItem(String item_name, Double item_starting_price, Double item_reserved_price, AuctionParticipant seller) {
        this.item_name = item_name;
        this.item_starting_price = item_starting_price;
        this.item_reserved_price = item_reserved_price;
        this.seller = seller;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_starting_price() {
        return item_starting_price;
    }

    public void setItem_starting_price(Double item_starting_price) {
        this.item_starting_price = item_starting_price;
    }

    public Double getItem_reserved_price() {
        return item_reserved_price;
    }

    public void setItem_reserved_price(Double item_reserved_price) {
        this.item_reserved_price = item_reserved_price;
    }

    public AuctionParticipant getSeller() {
        return seller;
    }

    public void setSeller(AuctionParticipant seller) {
        this.seller = seller;
    }

    public void setStatus(String status) {
       this.status = status;
    }

    public String getStatus (){
      return this.status;
    }
}
