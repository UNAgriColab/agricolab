package agricolab.service;

import agricolab.dao.OfferDAO;
import agricolab.dao.ProductoDAO;
import agricolab.model.Offer;
import agricolab.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OfferService {

    private OfferDAO offerDAO;
    private ProductoDAO productoDAO;

    @Autowired
    public OfferService(OfferDAO offerdao, ProductoDAO productoDAO) {
        this.offerDAO = offerdao;
        this.productoDAO = productoDAO;
    }

    public boolean productoValdo(String producto){
        boolean coinsidence = false;
        for(Producto p : productoDAO.getProductos()){
            for(String productName : p.getProductNames() ){
                if (productName.equals(producto)) {
                    coinsidence = true;
                    break;
                }
            }
        }
        return coinsidence;
    }

    public boolean addOffer(Offer offer) {
        if(productoValdo(offer.getProductName()) && offerDAO.getOffersByUserAndProduct(offer.getSellerEmail() , offer.getProductName()).isEmpty()){
            return offerDAO.createOffer(offer);
        }else {
            return false;
        }
    }

    public boolean postProduct(Producto producto){
        return productoDAO.addProduct(producto);
    }

    public Offer getOffer(String id) {
        return offerDAO.getOffer(id);
    }

    public boolean updateOffer(Offer offer) {
        return offerDAO.updateOffer(offer);
    }

    public ArrayList<Offer> getAllOffers() {
        return offerDAO.getAllOffers();
    }

    public ArrayList<Offer> gerOffersByUser(String email) {
        return offerDAO.getOffersByUser(email);
    }

    //filtros
    public ArrayList<Offer> getActiveOffers(String productName ,double minPrice, double maxPrice , int presentation ,  int order) {
        ArrayList<Offer> offers =  new ArrayList<>();
        ArrayList<Offer> ofertas = offerDAO.getActiveOffers(productName , minPrice , maxPrice , presentation , order );
        if((order == 3) && ((minPrice != 0) || (maxPrice !=0))) {
            if((minPrice != 0) && (maxPrice !=0)){
                for (Offer o: ofertas){
                    if (o.getPricePresentation()<=maxPrice && o.getPricePresentation()>=minPrice){
                        offers.add(o);
                    }
                }
            }
            if((minPrice != 0) && (maxPrice ==0)){
                for (Offer o: ofertas){
                    if (o.getPricePresentation()>=minPrice){
                        offers.add(o);
                    }
                }
            }
            if((minPrice == 0) && (maxPrice !=0)){
                for (Offer o: ofertas){
                    if (o.getPricePresentation()<=maxPrice){
                        offers.add(o);
                    }
                }
            }
        }else{
            offers = ofertas;

        }
        return offers;
    }

    public void deleteOffer(String id) {
        offerDAO.deleteOffer(id);
    }

    public int getLastOfferId() {
        return offerDAO.getLastOfferId();
    }

}
