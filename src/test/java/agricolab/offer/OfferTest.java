package agricolab.offer;


import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class OfferTest {

    private final OfferService offerService;

    @Autowired
    public OfferTest(OfferService offerService) {
        this.offerService = offerService;
    }

    @Test
    public void creationTest(){
        Offer o = new Offer();
        o.manual("test1@test.com","Papa",1, 21.4 , 23 , "sin descripcion");
        String name = null;
        try {
            offerService.addOffer(o);
            Thread.sleep(5000);
            name = offerService.getOffer(Integer.toString(offerService.getLastOfferId())).getUserEmail();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(name,"test1@test.com");
    }

    @Test
    public void deleteTest(){
        Offer o = new Offer();
        try {
            offerService.deleteOffer(Integer.toString(offerService.getLastOfferId()));
            Thread.sleep(5000);
            o = offerService.getOffer(Integer.toString(offerService.getLastOfferId()));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNull(o);
    }

    @Test
    public void getBuyerOffersTest(){
        ArrayList<Offer> offers;
        String email = "samoralespu@unal.edu.co";
        try {
            offers = offerService.gerOffersByUser("samoralespu@unal.edu.co");
            Thread.sleep(5000);
            for (Offer o : offers){
                if(!email.equals("samoralespu@unal.edu.co"))
                    email = o.getUserEmail();
            }
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(email, "samoralespu@unal.edu.co");
    }
}

