package agricolab.service;

import agricolab.dao.OfferDAO;
import agricolab.dao.OrderDAO;
import agricolab.dao.UserDAO;
import agricolab.model.Mailing;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final OrderDAO orderDAO;
    private final OfferDAO offerDAO;


    @Autowired
    public UserService(@Qualifier("UserFirestore") UserDAO userDAO, OrderDAO orderDao, OfferDAO offerDAO) {

        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userDAO = userDAO;
        this.orderDAO = orderDao;
        this.offerDAO = offerDAO;
    }

    public boolean addUser(User user) {
        for (User u : userDAO.getAllUsers()) {
            if (u.getEmail().equals(user.getEmail())) {
                System.out.println("ya existe un usuario registrado con este correo, por favor intenta te nuevo");
                return false;
            }
        }
        if (user.getAge() < (18)) {
            System.out.println("debes ser mayor de edad para hacer uso de nuestra herramienta");
            return false;
        }
        if (user.getPassword().matches("[0-9A-F]+")) {
            System.out.println("contraseña invalida, debe contener almenos un numero y una mayuscula");
            return false;
        }
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userDAO.createUser(user);
    }


    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(String email) {
        return userDAO.deleteUser(email);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUser(s);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public Mailing getMailingByUser(String email) throws ExecutionException, InterruptedException {
        return userDAO.getMailingByUser(email);
    }

    public boolean createMailing(String email, Mailing mailing) {
        User user = getUser(email);
        user.setMailing(mailing);
        return userDAO.createMailing(user);//, mailing);
    }

    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    public boolean updateUserData(String email, Mailing mailing, long phoneNumber) {
        return userDAO.updateUserData(email, mailing, phoneNumber);
    }

    public boolean updateUserQualification(String email, double qualification) {
        return userDAO.updateUserQualification(email, qualification);
    }

    public ArrayList<Integer> getDashboard(String email) {
        ArrayList<Integer> data = new ArrayList<>();
        User u = userDAO.getUser(email);
        data.add(u.getNumberOfOrdersDone());
        data.add(u.getNumberOfOrdersRecieved());
        if (u.getMailing().getCity() == null) {
            data.add(1);
        } else {
            data.add(0);
        }
        data.add(offerDAO.getAllOffers().size()+1);
        return data;
    }

    //ordenes que recibe un usuario vendedor
    public void updateOrdersRecieved(String email, int newNum) {
        userDAO.updateOffersRecieved(email, newNum);
    }

    //ordenes que hace un usuario comprador
    public void updateOrdersMade(String email, int newNum) {
        userDAO.updateOffersMade(email, newNum);
    }
}