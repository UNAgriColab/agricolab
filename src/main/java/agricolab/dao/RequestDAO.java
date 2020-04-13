package agricolab.dao;

import agricolab.model.Request;
import java.util.ArrayList;

public interface RequestDAO {

    int createRequest(Request request);

    Request getRequest(String id);

    int updateRequest(Request r1, Request r2);

    int deleteRequest(Request r);

    ArrayList<Request> getAllRequest();
}
