package agricolab.api;

import agricolab.JsonModel.Update;
import agricolab.model.Order;
import agricolab.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderAPI {

    private final OrderService orderService;

    @Autowired
    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void postOrder(@RequestBody Order r) {
        orderService.addOrder(r);
    }

    @DeleteMapping("del/{id}")
    public void deleteOrder(@PathVariable String id ) { orderService.deleteOrder(id);}

    // GET METHODS
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PutMapping("/buyer")
    public boolean updateOrderByBuyer(@RequestBody Update changes){
        return orderService.updateOrderByBuyer(changes);
    }
    @PutMapping("/seller")
    public boolean updateOrderBySeller(@RequestBody Update changes){
        return orderService.updateOrderBySeller(changes);
    }
    @GetMapping
    public ArrayList<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{email}")
    public ArrayList<Order> getOrdersByBuyer(@PathVariable String email) {
        return orderService.getOrdersByBuyer(email);
    }

    @GetMapping("/offer/{offerId}")
    public ArrayList<Order> getOrdersByOffer(@PathVariable String offerId) {
        return orderService.getOrdersByOffer(offerId);
    }

    @GetMapping("/seller/{email}")
    public ArrayList<Order> getOrdersBySeller(@PathVariable String email){
        return orderService.getOrdersBySeller(email);
    }

}
