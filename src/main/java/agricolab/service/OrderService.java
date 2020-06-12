package agricolab.service;

import agricolab.JsonModel.Update;
import agricolab.dao.OrderDAO;
import agricolab.model.Comment;
import agricolab.model.Offer;
import agricolab.model.Order;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;


@Service
public class OrderService {

    private final OrderDAO orderDAO;
    private final UserService userService;
    private final OfferService offerService;
    private final CommentService commentService;

    @Autowired
    public OrderService(OrderDAO orderDAO, OfferService offerService, UserService userService, CommentService commentService) {
        this.orderDAO = orderDAO;
        this.offerService = offerService;
        this.userService = userService;
        this.commentService = commentService;
    }

    public boolean addOrder(Order order) {
        // Check for orders on the same product

        // Now we populate the order fields with the product data
        Offer offerFromRef = offerService.getOffer(order.getOfferReference());
        order.setSellerEmail(Objects.requireNonNull(offerFromRef).getSellerEmail());
        order.setProductName(Objects.requireNonNull(offerFromRef).getProductName());
        order.setPresentation(Objects.requireNonNull(offerFromRef).getPresentation());
        order.setTotalPrice(Objects.requireNonNull(offerFromRef).getPricePresentation() * order.getNumberOfUnits());

        // Check for different buyer and seller (return false)
        if (order.getBuyerEmail().equalsIgnoreCase(order.getSellerEmail())) {
            System.out.println("You can't create an order request from yourself.");
            return false;
        }

        // Check for minimum order quantity
        if (order.getNumberOfUnits() < offerService.getOffer(order.getOfferReference()).getMinQuantity()) {
            System.out.println("You are attempting to buy less units than the minimum.");
            return false;
        }

        // No inconsistencies, delegate return to DAO status
        return orderDAO.createOrder(order);
    }

    public Order getOrder(String id) {
        return orderDAO.getOrder(id);
    }

    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public ArrayList<Order> getOrdersByBuyer(String email, String productName, int state) {
        return orderDAO.getOrdersByBuyer(email, productName, state);
    }

    public ArrayList<Order> getActiveOrdersByBuyer(String email, String productName, int state) {
        return orderDAO.getActiveOrdersByBuyer(email, productName, state);
    }

    public ArrayList<Order> getOrdersBySeller(String email, String productName, int state) {
        return orderDAO.getOrdersBySeller(email, productName, state);
    }

    public ArrayList<Order> getActiveOrdersBySeller(String email, String productName, int state) {
        return orderDAO.getActiveOrdersBySeller(email, productName, state);
    }

    public void deleteOrder(String id) {
        orderDAO.deleteOrder(id);
    }

    //update methods
    public boolean updateOrderByBuyer(Update changes) {
        return orderDAO.updateOrderByBuyer(changes.getOrderId());
    }

    public boolean updateOrderBySeller(Update changes) {
        return orderDAO.updateOrderBySeller(changes);
    }

    public boolean updateOrderQualification(Comment comment) {
        if ((1 <= comment.getCalificacion() && comment.getCalificacion() <= 5) && (orderDAO.getOrder(comment.getOrderReference()).getState() == 4)) {

            Order order = orderDAO.getOrder(comment.getOrderReference());
            if (order.getQualification() != 0) {
                System.out.println("Ya se habia realizado un review de esta orden, no se puede hacer 2 veces");
                return false;
            }
            User u = userService.getUser(order.getSellerEmail());
            Offer o = offerService.getOffer(order.getOfferReference());
            commentService.addComment(comment);

            double newQualification = ((u.getQualification() * u.getNumberOfReviews()) + comment.getCalificacion()) / (u.getNumberOfReviews() + 1);

            u.setQualification(newQualification);
            u.setNumberOfReviews(u.getNumberOfReviews() + 1);
            userService.updateUser(u);

            int offerQualification = ((o.getQualification() * o.getNumberOfReviews()) + comment.getCalificacion()) / (o.getNumberOfReviews() + 1);
            o.setQualification(offerQualification);
            o.setNumberOfReviews(o.getNumberOfReviews() + 1);
            offerService.updateOffer(o);

            order.setQualification(comment.getCalificacion());
            return orderDAO.updateOrder(order);
        } else {
            System.out.println("calificacion fuera de rango no puede ser procesada");
            return false;
        }
    }
}
