package com.example.agricolab.dao;

import com.example.agricolab.model.Request;
import java.util.UUID;

public interface RequestDAO {

    int createRequest(UUID id, Request request);

    default int createRequest(Request u) {
        UUID id = UUID.randomUUID();
        return createRequest(id, u);
    }

    int readRequest(UUID id);

    int updateRequest(Request r1, Request r2);

    int deleteRequest(Request r);
}
