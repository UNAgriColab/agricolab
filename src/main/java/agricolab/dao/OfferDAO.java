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
    ArrayList<Offer> getActiveOffers(String productName , double maxPrice , int presentation);

    ArrayList<Offer> getActiveOffers(String productName , double maxPrice);

    ArrayList<Offer> getActiveOffers(String productName , int presentation);

    ArrayList<Offer> getActiveOffers(double maxPrice , int presentation);

    ArrayList<Offer> getActiveOffers(String productName);

    ArrayList<Offer> getActiveOffers(double maxPrice );

    ArrayList<Offer> getActiveOffers(int presentation);

    ArrayList<Offer> getActiveOffers();

    ArrayList<Offer> getOffersByUserAndProduct(String email , String productName);

    void deleteOffer(String id);
}
