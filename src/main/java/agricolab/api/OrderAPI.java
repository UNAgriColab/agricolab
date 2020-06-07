package agricolab.api;

import agricolab.JsonModel.Update;
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

    @DeleteMapping("del/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
    }

    // GET METHODS
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PutMapping("/buyer")
    public boolean updateOrderByBuyer(@RequestBody Update changes) {
        return orderService.updateOrderByBuyer(changes);
    }

    @PutMapping("/seller")
    public boolean updateOrderBySeller(@RequestBody Update changes) {
        return orderService.updateOrderBySeller(changes);
    }

    @GetMapping
    public ArrayList<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("/buyer/{email}")
    public ArrayList<Order> getOrdersByBuyer(@PathVariable String email) {
        return orderService.getOrdersByBuyer(email);
    }

    @GetMapping("/seller/{email}")
    public ArrayList<Order> getOrdersBySeller(@PathVariable String email) {
        return orderService.getOrdersBySeller(email);
    }

    @GetMapping("/seller/actives/{email}/{productName}")
    public ArrayList<Order> getActiveOrdersBySeller(@PathVariable String email , @PathVariable String productName) {
        return orderService.getActiveOrdersBySeller(email , productName);
    }

    @GetMapping("/buyer/actives/{email}")
    public ArrayList<Order> getActiveOrdersByBuyer(@PathVariable String email) {
        return orderService.getActiveOrdersByBuyer(email);
    }
    @PostMapping("/qualification")
    public boolean updateOrderQualification(@RequestBody Comment comment){
        return orderService.updateOrderQualification(comment);
    }
}


