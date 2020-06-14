package agricolab.service;

import agricolab.dao.OfferDAO;
import agricolab.dao.ProductoDAO;
import agricolab.model.Offer;
import agricolab.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


@Service
public class OfferService {

    private final OfferDAO offerDAO;
    private final ProductoDAO productoDAO;

    @Autowired
    public OfferService(OfferDAO offerdao, ProductoDAO productoDAO) {
        this.offerDAO = offerdao;
        this.productoDAO = productoDAO;
    }

    public boolean productoValdo(String producto) {
        boolean coinsidence = false;
        for (Product p : productoDAO.getProductos()) {
            for (String productName : p.getProductNames()) {
                if (productName.equals(producto)) {
                    coinsidence = true;
                    break;
                }
            }
        }
        return coinsidence;
    }

    public boolean addOffer(Offer offer) {
        if (productoValdo(offer.getProductName()) && offerDAO.getOffersByUserAndProduct(offer.getSellerEmail(), offer.getProductName()).isEmpty()) {
            return offerDAO.createOffer(offer);
        } else {
            return false;
        }
    }

    public boolean postProduct(Product product) {
        return productoDAO.addProduct(product);
    }

    public Offer getOffer(String id) {
        return offerDAO.getOffer(id);
    }

    public boolean updateOffer(Offer offer) {
        return offerDAO.updateOffer(offer);
    }

    public ArrayList<Offer> getAllOffers() {
        return offerDAO.getAllOffers();
    }

    public ArrayList<Offer> gerOffersByUser(String email) {
        return offerDAO.getOffersByUser(email);
    }

    //filtros
    public ArrayList<Offer> getActiveOffers(String productName, double minPrice, double maxPrice,
                                            int presentation, int order, int page, int pivot) {
        ArrayList<Offer> offers = new ArrayList<>();
        ArrayList<Offer> ofertas = null;
        try {
            ofertas = offerDAO.getActiveOffers(productName, minPrice, maxPrice, presentation,
                    order, page, pivot);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if ((order == 3 || order == 0) && ((minPrice != 0) || (maxPrice != 0))) {
            do {
                //filtrar por rango de precio en caso de necesitar un ordenamiento diferente
                if ((minPrice != 0) && (maxPrice != 0)) {
                    for (Offer o : Objects.requireNonNull(ofertas)) {
                        if (page == 0) {
                            ofertas = invert(ofertas);
                        }
                        if (o.getPricePresentation() <= maxPrice && o.getPricePresentation() >= minPrice) {
                            if (offers.size() < 10) {
                                offers.add(o);
                                System.out.println("SE AÑADIO= " + o.getId());
                            }
                        }
                    }
                } else if ((minPrice != 0) && (maxPrice == 0)) {
                    for (Offer o : Objects.requireNonNull(ofertas)) {
                        if (o.getPricePresentation() >= minPrice) {
                            if (offers.size() < 10) {
                                offers.add(o);
                            }
                        }
                    }
                } else if ((minPrice == 0) && (maxPrice != 0)) {
                    for (Offer o : Objects.requireNonNull(ofertas)) {
                        if (o.getPricePresentation() <= maxPrice) {
                            if (offers.size() < 10) {
                                offers.add(o);
                            }
                        }
                    }
                }

                try {
                    if (page == 0 && ofertas.size() != 0) {
                        System.out.println("pagina anterior y tamaño de ofertas distinto de 0");
                        ofertas = offerDAO.getActiveOffers(productName, minPrice, maxPrice, presentation,
                                order, 0, ofertas.get(ofertas.size() - 1).getId());
                    } else if (ofertas.size() != 0){
                        System.out.println("pagina siguiente y tamaño de ofertas igual a 0");
                        ofertas = offerDAO.getActiveOffers(productName, minPrice, maxPrice, presentation,
                                order, 2, ofertas.get(ofertas.size() - 1).getId());
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }while (offers.size() < 10 && Objects.requireNonNull(ofertas).size() != 0);
            if (page == 0){
                offers = invert(offers);
            }
            return offers;
        }
        return ofertas;
    }

    private ArrayList<Offer> invert(ArrayList<Offer> ofertas) {
        ArrayList<Offer> inverted = new ArrayList<>();
        for (int i = Objects.requireNonNull(ofertas).size(); i > 0; i--) {
            inverted.add(ofertas.get(i - 1));
        }
        return inverted;
    }

    public boolean deleteOffer(String id) {
        return offerDAO.deleteOffer(id);
    }

    public int getLastOfferId() {
        return offerDAO.getLastOfferId();
    }

}
