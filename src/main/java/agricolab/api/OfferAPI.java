package agricolab.api;

import agricolab.model.Offer;
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

    @PostMapping
    public void createOffer(@RequestBody Offer o) {
        offerService.addOffer(o);
    }

    @PutMapping("/{id}")
    public void updateOffer(@PathVariable int id, @RequestBody Offer o) {
        offerService.updateOrder(id, o);
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
    public ArrayList<Offer> getUserOffers(@PathVariable String email) {
        return offerService.gerOffersByUser(email);
    }


}
