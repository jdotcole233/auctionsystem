import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuctionProjectServer{
    public static void main(String[]args)throws Exception{
        AuctionProjectInterfaceImplementation auc = new AuctionProjectInterfaceImplementation();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("AuctionBind",auc);
        System.out.println("Server connected successfully");

    }
}
