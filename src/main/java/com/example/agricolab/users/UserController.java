package com.example.agricolab.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UsersRepository usersRepository;

    @Autowired

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    @RequestMapping(value = "/find/{mail}" , method = RequestMethod.GET)
    public List<Users> getAffordable(@PathVariable String mail)  {
        return usersRepository.findByEmail(mail);
    }


    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public List<Users> create (@RequestBody Users producto ){
        usersRepository.save(producto);
        return usersRepository.findAll();
    }
    @RequestMapping(value = "/delete/{id}")
    public List<Users> delete (@PathVariable long id){
        usersRepository.deleteById(id);
        return usersRepository.findAll();
    }
}
