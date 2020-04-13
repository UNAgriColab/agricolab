package agricolab.api;

import agricolab.model.Offers;
import agricolab.model.Request;
import agricolab.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/offer")
public class OfferAPI {

    private final OfferService offerService;

    @Autowired
    public OfferAPI(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    public Offers getOffer(@PathVariable String id) {
        return offerService.getOffer(id);
    }

    @PostMapping
    public void postRequest(@RequestBody Offers o) {
        offerService.addOffer(o);
    }

    @GetMapping
    public ArrayList<Offers> getAllUsers(){
        return offerService.getAllOffers();
    }

    @PutMapping
    public void putOffer() {
    }

    @DeleteMapping
    public void deleteOffer() {
    }

}
