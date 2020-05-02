package agricolab.dao;

import agricolab.model.ID;
import agricolab.model.Offer;

import java.util.ArrayList;

public interface OfferDAO {

    ID getOfferById ();

    int createOffer(Offer offer);

    Offer getOffer(String id);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> gerOffersByUser(String email);

    void deleteOffer(String id);
}
