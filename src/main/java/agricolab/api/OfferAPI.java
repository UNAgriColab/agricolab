package agricolab.api;

import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/offer")
@RestController
public class OfferAPI {

    private final OfferService offerService;

    @Autowired
    public OfferAPI(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public boolean postOffer(@RequestBody Offer o) {
        return offerService.addOffer(o);
    }

    @PutMapping
    public void updateOffer(@RequestBody Offer o) {
        offerService.updateOffer(o);
    }

    @DeleteMapping("del/{id}")
    public void deleteOffer(@PathVariable String id) {
        offerService.deleteOffer(id);
    }

    @GetMapping("/{id}")
    public Offer getOffer(@PathVariable String id) {
        return offerService.getOffer(id);
    }

    @GetMapping
    public ArrayList<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/user/{email}")
    public ArrayList<Offer> getOffersByUser(@PathVariable String email) {
        return offerService.gerOffersByUser(email);
    }

    @GetMapping("/{productName}/{maxPrice}/{presentation}")
    public ArrayList<Offer> getActiveOrders(@PathVariable String productName , @PathVariable double maxPrice , @PathVariable int presentation) {
        return offerService.getActiveOffers(productName ,  maxPrice , presentation);
    }
}
