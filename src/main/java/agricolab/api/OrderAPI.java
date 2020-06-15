package agricolab.api;

import agricolab.model.Comment;
import agricolab.model.Order;
import agricolab.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/order")
@RestController
public class OrderAPI {

    private final OrderService orderService;

    @Autowired
    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public boolean postOrder(@RequestBody Order r) {
        return orderService.addOrder(r);
    }

    @PutMapping("del/{id}")
    public boolean deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/update/{id}/{email}")
    public boolean updateOrderStatus(@PathVariable String id, @PathVariable String email) {
        return orderService.updateOrderStatus(id, email);
    }

    @PutMapping("/cancel/{id}/{email}")
    public boolean cancelOrder(@PathVariable String id, @PathVariable String email) {
        return orderService.cancelOrder(id, email);
    }

    // GET METHODS
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/last")
    public int getLastOfferId() {
        return orderService.getLastOrderId();
    }

    /*
     * ORDER ARRAYLIST GETTERS
     * */
    @GetMapping
    public ArrayList<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("/buyer/{email}/{productName}/{state}")
    public ArrayList<Order> getOrdersByBuyer(@PathVariable String email, @PathVariable String productName, @PathVariable int state) {
        return orderService.getOrdersByBuyer(email, productName, state);
    }

    @GetMapping("/buyer/actives/{email}/{productName}/{state}")
    public ArrayList<Order> getActiveOrdersByBuyer(@PathVariable String email, @PathVariable String productName, @PathVariable int state) {
        return orderService.getActiveOrdersByBuyer(email, productName, state);
    }

    @GetMapping("/seller/{email}/{productName}/{state}")
    public ArrayList<Order> getOrdersBySeller(@PathVariable String email, @PathVariable String productName, @PathVariable int state) {
        return orderService.getOrdersBySeller(email, productName, state);
    }

    @GetMapping("/seller/actives/{email}/{productName}/{state}")
    public ArrayList<Order> getActiveOrdersBySeller(@PathVariable String email, @PathVariable String productName, @PathVariable int state) {
        return orderService.getActiveOrdersBySeller(email, productName, state);
    }

    @PutMapping("/qualification")
    public boolean updateOrderQualification(@RequestBody Comment comment) {
        return orderService.updateOrderQualification(comment);
    }
}


