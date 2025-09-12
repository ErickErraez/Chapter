package com.library.patterns.adapter;

// Adapter para LegacyLibro
public class LegacyLibroAdapter implements ILibro {
    private final LegacyLibro legacyLibro;

    public LegacyLibroAdapter(LegacyLibro legacyLibro) {
        this.legacyLibro = legacyLibro;
    }

    @Override
    public Long getId() { return legacyLibro.obtenerId(); }
    @Override
    public String getTitle() { return legacyLibro.obtenerTitulo(); }
    @Override
    public String getAuthor() { return legacyLibro.obtenerAutor(); }
    @Override
    public String getType() { return legacyLibro.obtenerTipo(); }
    @Override
    public String getFormat() { return legacyLibro.obtenerFormato(); }
    @Override
    public String getStatus() { return legacyLibro.obtenerEstado(); }
}
