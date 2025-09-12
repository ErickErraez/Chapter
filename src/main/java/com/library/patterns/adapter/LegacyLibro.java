package com.library.patterns.adapter;

// Simula una clase de sistema antiguo
public class LegacyLibro {
    private Long legacyId;
    private String legacyTitulo;
    private String legacyAutor;
    private String legacyTipo;
    private String legacyFormato;
    private String legacyEstado;

    public LegacyLibro(Long id, String titulo, String autor, String tipo, String formato, String estado) {
        this.legacyId = id;
        this.legacyTitulo = titulo;
        this.legacyAutor = autor;
        this.legacyTipo = tipo;
        this.legacyFormato = formato;
        this.legacyEstado = estado;
    }

    public Long obtenerId() { return legacyId; }
    public String obtenerTitulo() { return legacyTitulo; }
    public String obtenerAutor() { return legacyAutor; }
    public String obtenerTipo() { return legacyTipo; }
    public String obtenerFormato() { return legacyFormato; }
    public String obtenerEstado() { return legacyEstado; }
}
