package agricolab.user;

import agricolab.service.UserService;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class UserTest {

    private final UserService userService;

    @Autowired
    public UserTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void creationTest(){
        User u = new User();
        u.manual("test1@test.com","test1","12345", 21);
        String name = null;
        try {
            userService.addUser(u);
            Thread.sleep(5000);
            name = userService.getUser("test1@test.com").getName();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(name,"test1");
    }

    @Test
    public void deleteTest(){

        try {
            userService.deleteUser("test1@test.com");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User u = userService.getUser("test1@test.com");
        assertNull(u);
    }
}
