package agricolab.user;

import agricolab.service.UserService;
import agricolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


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
        u.manual("test1@test.com","test1","12345", 13);
        userService.addUser(u);
        String name = "";
        name = userService.getUser("test1@test.com").getName();
        System.out.print(name + "expected test1");
        assertEquals(name,"test1");
    }

    @Test
    public void deleteTest(){
        userService.deleteUser("test1@test.com");
        User u = userService.getUser("test1@test.com");
        assertEquals(u, null);
    }
}
