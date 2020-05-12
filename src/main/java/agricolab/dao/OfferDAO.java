package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;
import agricolab.model.Order;

import java.util.ArrayList;

public interface OfferDAO {

    ID setOfferId ();

    int createOffer(Offer offer);

    Offer getOffer(String id);

    boolean updateOffer(Offer r);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> gerOffersByUser(String email);

    void deleteOffer(String id);
}
