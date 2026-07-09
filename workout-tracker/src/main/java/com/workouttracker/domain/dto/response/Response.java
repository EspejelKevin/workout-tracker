package com.workouttracker.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    private Object data;
    private Meta meta;

    public Response(Object data, String transactionID) {
        this.data = data;
        this.meta = new Meta(transactionID);
    }
}
