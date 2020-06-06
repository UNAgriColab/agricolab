package agricolab.api;

import agricolab.model.Offer;
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

    @GetMapping("/last")
    public int getLastOfferId() {
        return offerService.getLastOfferId();
    }

    @GetMapping
    public ArrayList<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/actives")
    public ArrayList<Offer> getActiveOffers() {
        return offerService.getActiveOffers();
    }

    @GetMapping("/user/{email}")
    public ArrayList<Offer> getOffersByUser(@PathVariable String email) {
        return offerService.gerOffersByUser(email);
    }

    @GetMapping("product/{productName}")
    public ArrayList<Offer> getOffersByProduct(@PathVariable String productName) {
        return offerService.getOffersByProduct(productName);
    }
}
