package com.workouttracker.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meta {
    @JsonProperty("transaction_id")
    private String transactionID;

    private LocalDateTime timestamp;

    public Meta(String transactionID) {
        this.transactionID = transactionID;
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
    }
}
