package agricolab.api;

import agricolab.model.Comment;
import agricolab.model.Offer;
import agricolab.model.Product;
import agricolab.service.CommentService;
import agricolab.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/offer")
@RestController
public class OfferAPI {

    private final OfferService offerService;
    private final CommentService commentService;

    @Autowired
    public OfferAPI(OfferService offerService, CommentService commentService) {
        this.offerService = offerService;
        this.commentService = commentService;
    }

    @PostMapping
    public boolean postOffer(@RequestBody Offer o) {
        return offerService.addOffer(o);
    }

    @PutMapping
    public void updateOffer(@RequestBody Offer o) {
        offerService.updateOffer(o);
    }

    @PutMapping("del/{id}")
    public boolean deleteOffer(@PathVariable String id) {
        return offerService.deleteOffer(id);
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

    @GetMapping("/user/{email}")
    public ArrayList<Offer> getOffersByUser(@PathVariable String email) {
        return offerService.gerOffersByUser(email);
    }

    @GetMapping("/{productName}/{maxPrice}/{presentation}/{minPrice}/{order}/{page}/{pivot}")
    public ArrayList<Offer> getActiveOrders(@PathVariable String productName, @PathVariable double maxPrice, @PathVariable int presentation,
                                            @PathVariable double minPrice, @PathVariable int order, @PathVariable int page,
                                            @PathVariable int pivot){
        return offerService.getActiveOffers(productName, minPrice, maxPrice, presentation, order, page, pivot);
    }

    @GetMapping("/comments/{offerID}")
    public ArrayList<Comment> getAllCommentsByOffer(@PathVariable String offerID) {
        return commentService.getAllCommentsByOffer(offerID);
    }

    @PostMapping("product")
    public boolean postProduct(@RequestBody Product product) {
        return offerService.postProduct(product);
    }

}
