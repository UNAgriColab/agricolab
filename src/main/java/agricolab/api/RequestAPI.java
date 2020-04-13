package agricolab.api;

import agricolab.model.Request;
import agricolab.model.User;
import agricolab.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/request")
public class RequestAPI {

    private final RequestService requestService;

    @Autowired
    public RequestAPI(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public void postRequest(@RequestBody Request r) {
        requestService.addRequest(r);
    }

    @PutMapping
    public void putRequest() {
    }

    @DeleteMapping
    public void deleteRequest() {
    }

    // GET METHODS
    @GetMapping("/{id}")
    public Request getRequest(@PathVariable String id) {
        return requestService.getRequest(id);
    }

    @GetMapping
    public ArrayList<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/user/{email}")
    public ArrayList<Request> getUserRequests(@PathVariable String email) {
        return requestService.getUserRequests(email);
    }

}
