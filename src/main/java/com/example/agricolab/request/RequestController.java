package com.example.agricolab.request;

import com.example.agricolab.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/request")
public class RequestController {
    private RequestRepository requestRepository;

    @Autowired

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Request> getAll(){
        return requestRepository.findAll();
    }

    @RequestMapping(value = "/find/{name}" , method = RequestMethod.GET)
    public List<Request> getAffordable(@PathVariable String name)  {
        return requestRepository.findByProductName(name);
    }


    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public List<Request> create (@RequestBody Request request ){
        requestRepository.save(request);
        return requestRepository.findAll();
    }
    @RequestMapping(value = "/delete/{id}")
    public List<Request> delete (@PathVariable long id){
        requestRepository.deleteById(id);
        return requestRepository.findAll();
    }
}
