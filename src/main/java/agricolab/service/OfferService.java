package agricolab.service;

import agricolab.dao.OfferDAO;
import agricolab.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OfferService {

    private OfferDAO offerDAO;

    @Autowired
    public OfferService(OfferDAO offerdao) {
        this.offerDAO = offerdao;
    }

    public boolean addOffer(Offer offer) {
        // Check for orders on the same product
        if (!offerDAO.getOffersByUserAndProduct(offer.getSellerEmail() , offer.getProductName()).isEmpty()){
            System.out.println("ya hiciste una oferta de este producto y sigue activa, debes esperar a su" +
                    " fin o cancelarla antes de crear otra");
            return false;
        }
        return offerDAO.createOffer(offer);
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
    public ArrayList<Offer> getActiveOffers(String productName ,double minPrice, double maxPrice , int presentation ,  int order) {return offerDAO.getActiveOffers(productName , minPrice , maxPrice , presentation , order);
    }

    public void deleteOffer(String id) {
        offerDAO.deleteOffer(id);
    }

    public int getLastOfferId() {
        return offerDAO.getLastOfferId();
    }

}
