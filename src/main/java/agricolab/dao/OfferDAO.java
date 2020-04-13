package agricolab.dao;

import agricolab.model.Offer;

import java.util.ArrayList;

public interface OfferDAO {

    int createOffer(Offer offer);

    Offer getOffer(String id);

    ArrayList<Offer> getAllOffers();

    ArrayList<Offer> getUserOffers(String email);
}
