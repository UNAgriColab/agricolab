package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;
import agricolab.model.Order;

import java.util.ArrayList;

public interface OfferDAO {

    ID setOfferId ();

    int getLastOfferId();

    int createOffer(Offer offer);

    Offer getOffer(String id);

    boolean updateOffer(int id, Offer r);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> gerOffersByUser(String email);

    void deleteOffer(String id);
}
