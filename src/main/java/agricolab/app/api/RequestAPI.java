package com.example.agricolab.api;

import com.example.agricolab.model.Request;
import com.example.agricolab.model.User;
import com.example.agricolab.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1//request")
public class RequestAPI {

    private final RequestService requestService;

    @Autowired
    public RequestAPI(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public User getRequest() {
        return null;
    }

    @PostMapping
    public String postRequest(@RequestBody Request r) {
        return requestService.addRequest(r);
    }

    @PutMapping
    public void putRequest() {
    }

    @DeleteMapping
    public void deleteRequest() {
    }
}
