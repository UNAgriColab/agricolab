package agricolab.order;

import agricolab.service.OrderService;
import agricolab.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class OrderTest {

    private final OrderService orderService;

    @Autowired
    public OrderTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    public void creationTest(){
        Order o = new Order();
        //    public void manual (String userEmail, String offerReference, int numberOfUnits, int unit , double totalPrice , String description) {
        o.manual("test1@test.com","2",1, 21 , 23.2 , "sin descripcion");
        String name = null;
        try {
            orderService.addOrder(o);
            Thread.sleep(5000);
            name = orderService.getOrder(Integer.toString(orderService.getLastOrderId())).getUserEmail();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(name,"test1@test.com");
    }

    @Test
    public void deleteTest(){
        Order u = new Order();
        try {
            orderService.deleteOrder(Integer.toString(orderService.getLastOrderId()));
            Thread.sleep(5000);
            u = orderService.getOrder(Integer.toString(orderService.getLastOrderId()));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNull(u);
    }

    @Test
    public void getBuyerOrdersTest(){
        ArrayList<Order> orders;
        String email = "test2@unal.edu.co";
        try {
            orders = orderService.getOrdersByBuyer("test2@unal.edu.co");
            Thread.sleep(5000);
            for (Order o : orders) {
                if (email == "samoralespu@unal.edu.co")
                    email = o.getUserEmail();
            }
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(email, "test2@unal.edu.co");
    }

}
