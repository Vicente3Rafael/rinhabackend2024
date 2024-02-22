package com.rinhabackend.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class TransacaoRequest {

    @NotBlank
    private int valor;

    @NotBlank
    @Size(min = 1, max = 1)
    private String tipo;

    @NotBlank
    @Size(min = 1, max = 10)
    private String descricao;
}
