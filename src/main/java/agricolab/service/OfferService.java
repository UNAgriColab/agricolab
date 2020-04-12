package agricolab.service;

import agricolab.dao.OffersDAO;
import agricolab.model.Offers;
import agricolab.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OfferService {


    private static OffersDAO offerDAO;

    @Autowired
    public OfferService(OffersDAO offerdao) {
        this.offerDAO = offerdao;
    }

    public int addOffer(Offers offer){
        return offerDAO.createOffer(offer);
    }

    public Offers getOffer(Long id){
        return offerDAO.getOffer(id);
    }

    public ArrayList<Offers> getAllOffers(){
        return offerDAO.getAllOffers();
    }
}
