package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;
import agricolab.model.Order;

import java.util.ArrayList;

public interface OfferDAO {

    ID setOfferId ();

    int getLastOfferId();

    boolean createOffer(Offer offer);

    Offer getOffer(String id);

    boolean updateOffer(Offer r);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> getOffersByUser(String email);

    ArrayList<Offer> getActiveOffers();

    ArrayList<Offer> getOffersByProduct(String productName);

    void deleteOffer(String id);
}
