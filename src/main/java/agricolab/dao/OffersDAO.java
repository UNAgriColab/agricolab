package agricolab.dao;

import agricolab.model.Offers;

import java.util.UUID;

public interface OffersDAO  {
    int createOffer(UUID id, Offers offers);

    default int createUser(Offers u) {
        UUID id = UUID.randomUUID();
        return createOffer(id, u);
    }

    int readOffer(UUID id);

    int updateOffer(Offers o1, Offers o2);

    int deleteOffer(Offers o);
}
