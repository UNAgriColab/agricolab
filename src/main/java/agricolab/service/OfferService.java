package agricolab.service;

import agricolab.dao.OffersDAO;
import agricolab.model.Offers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class OfferService {

    @Autowired
    @Qualifier("Firestore")
    private static OffersDAO offerDAO;

    public OfferService(OffersDAO offerdao) {
        this.offerDAO = offerdao;
    }

    public String addOffer(Offers offer) {
        int error = offerDAO.createUser(offer);
        if (error == 0) {
            return "Sucessfully added User.";
        } else {
            return "Unable to add User. Error: " + error;
        }
    }
}
