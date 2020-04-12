package agricolab.service;

import agricolab.dao.OfferDAO;
import agricolab.model.Offers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OfferService {


    private static OfferDAO offerDAO;

    @Autowired
    public OfferService(OfferDAO offerdao) {
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
