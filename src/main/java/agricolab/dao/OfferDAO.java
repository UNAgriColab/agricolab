package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;

import java.util.ArrayList;

public interface OfferDAO {

    ID setOfferId();

    int getLastOfferId();

    boolean createOffer(Offer offer);

    Offer getOffer(String id);

    boolean updateOffer(Offer r);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> getOffersByUser(String email);

    //methods used to the filters
    ArrayList<Offer> getActiveOffers(String productName ,double minPrice , double maxPrice , int presentation ,  int order );

    ArrayList<Offer> getOffersByUserAndProduct(String email, String productName);

    void deleteOffer(String id);


}
