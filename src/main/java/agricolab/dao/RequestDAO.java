package agricolab.dao;

import agricolab.model.Request;

import java.util.ArrayList;

public interface RequestDAO {

    int createRequest(Request request);

    Request getRequest(String id);

    ArrayList<Request> getAllRequests();

    ArrayList<Request> getUserRequests(String email);

    int updateRequest(Request r1, Request r2);

    int deleteRequest(Request r);


}
