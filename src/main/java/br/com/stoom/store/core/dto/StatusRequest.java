package br.com.stoom.store.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class StatusRequest {

    @NotEmpty(message = "status required")
    private String status;

}