package com.example.agricolab.offers;

import com.example.agricolab.users.Users;
import com.example.agricolab.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offer")
public class OfferController {
    private OffersRepository offersRepository;

    @Autowired

    public OfferController(OffersRepository offersRepository) {
        this.offersRepository = offersRepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Offers> getAll(){
        return offersRepository.findAll();
    }

    @RequestMapping(value = "/find/{conv}" , method = RequestMethod.GET)
    public List<Offers> getAffordable(@PathVariable int conv)  {
        return offersRepository.findByConvention(conv);
    }

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public List<Offers> create (@RequestBody Offers ofer ){
        offersRepository.save(ofer);
        return offersRepository.findAll();
    }
    @RequestMapping(value = "/delete/{id}")
    public List<Offers> delete (@PathVariable long id){
        offersRepository.deleteById(id);
        return offersRepository.findAll();
    }
}
