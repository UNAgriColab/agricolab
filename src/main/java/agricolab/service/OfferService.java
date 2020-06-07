package agricolab.service;

import agricolab.dao.OfferDAO;
import agricolab.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OfferService {

    private OfferDAO offerDAO;

    @Autowired
    public OfferService(OfferDAO offerdao) {
        this.offerDAO = offerdao;
    }

    public boolean addOffer(Offer offer) {
        // Check for orders on the same product
        if (!offerDAO.getOffersByUserAndProduct(offer.getSellerEmail() , offer.getProductName()).isEmpty()){
            System.out.println("ya hiciste una oferta de este producto y sigue activa, debes esperar a su" +
                    " fin o cancelarla antes de crear otra");
            return false;
        }
        return offerDAO.createOffer(offer);
    }

    public Offer getOffer(String id) {
        return offerDAO.getOffer(id);
    }

    public boolean updateOffer(Offer offer) {
        return offerDAO.updateOffer(offer);
    }

    public ArrayList<Offer> getAllOffers() {
        return offerDAO.getAllOffers();
    }

    public ArrayList<Offer> gerOffersByUser(String email) {
        return offerDAO.getOffersByUser(email);
    }

    //filtros
    public ArrayList<Offer> getActiveOffers(String productName , double maxPrice , int presentation , double minPrice, int order) {

        if(minPrice == 0){
            if(productName.equals("all")){
                if(maxPrice==0){
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffers(order);
                    }else{
                        return offerDAO.getActiveOffers(presentation , order);
                    }
                }else{
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffersmax(maxPrice , order);
                    }else{
                        return offerDAO.getActiveOffers(maxPrice , presentation , order);
                    }
                }

            }else{
                if(maxPrice==0){
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffers(productName , order);
                    }else{
                        return offerDAO.getActiveOffers(productName , presentation , order);
                    }
                }else{
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffers(productName , maxPrice , order);
                    }else{
                        return offerDAO.getActiveOffers(productName , maxPrice ,presentation , order);
                    }
                }
            }
        }else{
            if(productName.equals("all")){
                if(maxPrice==0){
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffersmin(minPrice , order);
                    }else{
                        return offerDAO.getActiveOffers(presentation , minPrice , order);
                    }
                }else{
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffers(maxPrice , minPrice ,  order);
                    }else{
                        return offerDAO.getActiveOffers(maxPrice , presentation , minPrice, order);
                    }
                }

            }else{
                if(maxPrice==0){
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffers( minPrice , productName , order);
                    }else{
                        return offerDAO.getActiveOffers(productName , presentation , minPrice , order);
                    }
                }else{
                    if(presentation == 0 ){
                        return offerDAO.getActiveOffers(productName , maxPrice , minPrice , order);
                    }else{
                        return offerDAO.getActiveOffers(productName , maxPrice ,presentation , minPrice ,order);
                    }
                }
            }
        }
    }

    public void deleteOffer(String id) {
        offerDAO.deleteOffer(id);
    }

    public int getLastOfferId() {
        return offerDAO.getLastOfferId();
    }

}
