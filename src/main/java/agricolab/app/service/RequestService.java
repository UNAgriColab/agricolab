package com.example.agricolab.service;

import com.example.agricolab.dao.RequestDAO;
import com.example.agricolab.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class RequestService {

    @Autowired
    @Qualifier("Firestore")
    private static RequestDAO requestDAO;

    public RequestService(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    public String addRequest(Request request) {
        int error = requestDAO.createRequest(request);
        if (error == 0) {
            return "Sucessfully added User.";
        } else {
            return "Unable to add User. Error: " + error;
        }
    }
}
