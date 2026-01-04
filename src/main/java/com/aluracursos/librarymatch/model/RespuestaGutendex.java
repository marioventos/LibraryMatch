package com.aluracursos.librarymatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespuestaGutendex(
        Integer count,
        List<DatosLibro> results
){}
