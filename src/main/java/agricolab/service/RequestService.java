package agricolab.service;

import agricolab.dao.RequestDAO;
import agricolab.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RequestService {


    private  RequestDAO requestDAO;

    @Autowired
    public RequestService(RequestDAO requestDAO){
        this.requestDAO = requestDAO;
    }

    public int addRequest(Request request){
        return requestDAO.createRequest(request);
    }

    public Request getRequest(String id){
        return requestDAO.getRequest(id);
    }

    public ArrayList<Request> getAllRequests(){
        return requestDAO.getAllRequest();
    }
}
