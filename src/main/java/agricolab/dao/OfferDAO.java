package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface OfferDAO {

    ID setOfferId();

    int getLastOfferId();

    boolean createOffer(Offer offer);

    Offer getOffer(String id);

    boolean updateOffer(Offer r);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> getOffersByUser(String email);

    //methods used to the filters
    ArrayList<Offer> getActiveOffers(String productName, double minPrice, double maxPrice,
                                     int presentation, int order, int page, int pivot) throws ExecutionException, InterruptedException;

    ArrayList<Offer> getOffersByUserAndProduct(String email, String productName);

    boolean deleteOffer(String id);


    boolean updateOfferReviews(String id, double qualification, int numberOfReviews);

    ArrayList<Offer> getSuggestedOffers(String email);

}
