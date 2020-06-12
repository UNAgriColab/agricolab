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
    public UserService(@Qualifier("Firestore") UserDAO userDAO, OrderDAO orderDao, OfferDAO offerDAO) {

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
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userDAO.createUser(user);
    }

    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(String email) {
        userDAO.deleteUser(email);
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

    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    public boolean createMailing(String email, Mailing mailing) {
        User user = getUser(email);
        user.setMailing(mailing);
        System.out.println(mailing);
        return userDAO.createMailing(user);//, mailing);
    }

    public boolean updateUser(User u) {
        return userDAO.updateUser(u);
    }
}