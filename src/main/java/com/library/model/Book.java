package com.library.model;

public class Book {
	private Long id;
	private String title;
	private String author;
	private String type; // Ficción/No Ficción
	private String format; // Físico/Digital
	private String status; // Disponible/Prestado

	public Book(Long id, String title, String author, String type, String format, String status) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.type = type;
		this.format = format;
		this.status = status;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public String getFormat() { return format; }
	public void setFormat(String format) { this.format = format; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
}
