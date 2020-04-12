package agricolab.api;

import agricolab.model.Request;
import agricolab.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/request")
public class RequestAPI {

    private final RequestService requestService;

    @Autowired
    public RequestAPI(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable Long id) {
        return requestService.getRequest(id);
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
}
