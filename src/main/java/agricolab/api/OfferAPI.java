package agricolab.api;

import agricolab.model.Offers;
import agricolab.model.Request;
import agricolab.model.User;
import agricolab.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api/v1/offer")
public class OfferAPI {

    private final OfferService offerService;

    @Autowired
    public OfferAPI(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    public Offers getOffer(@PathVariable Long id) {
        return offerService.getOffer(id);
    }

    @PostMapping
    public void postRequest(@RequestBody Offers o) {
        offerService.addOffer(o);
    }

    @PutMapping
    public void putOffer() {
    }

    @DeleteMapping
    public void deleteOffer() {
    }

}
