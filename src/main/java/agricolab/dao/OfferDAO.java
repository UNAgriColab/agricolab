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
    ArrayList<Offer> getActiveOffers(String productName , double maxPrice , int presentation , int order);

    ArrayList<Offer> getActiveOffers(String productName , double maxPrice , int order);

    ArrayList<Offer> getActiveOffers(String productName , int presentation , int order);

    ArrayList<Offer> getActiveOffers(double maxPrice , int presentation , int order);

    ArrayList<Offer> getActiveOffers(String productName , int order);

    ArrayList<Offer> getActiveOffersmax(double maxPrice, int order);

    ArrayList<Offer> getActiveOffers(int presentation , int order);

    ArrayList<Offer> getActiveOffers(int order);

    ArrayList<Offer> getActiveOffers(String productName , double maxPrice , int presentation , double minPrice , int order);

    ArrayList<Offer> getActiveOffers(String productName , double maxPrice , double minPrice , int order);

    ArrayList<Offer> getActiveOffers(String productName , int presentation , double minPrice , int order);

    ArrayList<Offer> getActiveOffers(double maxPrice , int presentation , double minPrice , int order);

    ArrayList<Offer> getActiveOffers(double minPrice ,String productName ,int order);

    ArrayList<Offer> getActiveOffers(double maxPrice ,double minPrice , int order);

    ArrayList<Offer> getActiveOffers(int presentation , double minPrice , int order);

    ArrayList<Offer> getActiveOffersmin(double minPrice , int order);

    ArrayList<Offer> getOffersByUserAndProduct(String email , String productName);

    void deleteOffer(String id);
}
