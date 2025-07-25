package com.github.RafaelScarpelli.backend.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class RespostaErro {

    private LocalDateTime dataHora;
    private int codigo;
    private String erro;
    private String mensagem;
    private String caminho;
    private List<String> detalhes;

    public RespostaErro(int codigo, String erro, String menasagem, String caminho, List<String> detalhes) {
        dataHora = LocalDateTime.now();
        this.codigo = codigo;
        this.erro = erro;
        this.caminho = caminho;
        this.mensagem = menasagem;
        this.detalhes = detalhes;
    }
}
