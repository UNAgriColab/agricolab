package com.example.agricolab.Persistance;

//import com.example.agricolab.offers.OffersRepository;
//import com.example.agricolab.request.RequestRepository;
import com.example.agricolab.users.Users;
import com.example.agricolab.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

public class Seeder implements CommandLineRunner {
    //private OffersRepository offersRepository;
    //private RequestRepository requestRepository;
    private UsersRepository usersRepository;

    @Autowired
    public Seeder( UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void run(String... strings)throws Exception{

    }
}
