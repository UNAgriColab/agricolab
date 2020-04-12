package agricolab.dao;

import agricolab.model.Offers;

import java.util.ArrayList;

public interface OfferDAO {

    int createOffer(Offers offer);

    Offers getOffer(Long id);

    ArrayList<Offers> getAllOffers();
}
