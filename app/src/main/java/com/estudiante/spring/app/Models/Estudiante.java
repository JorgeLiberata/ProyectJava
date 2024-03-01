package com.estudiante.spring.app.Models;
//import com.estudiante.spring.app.Services.IdUnicoService;


public class Estudiante {
    private String idUnico;
    private int id;
    private String nombre;
    private int edad;
    private String genero;
    private String email;
    

    // Constructor, getters y setters
    public Estudiante() {
    }
// 
    public Estudiante(String idUnico, int id, String nombre, int edad, String email, String genero) {
        this.idUnico = idUnico;
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.genero = genero;
        
    }

    // Getters y setters
    public String getidUnico() {
        return idUnico;
    }

    public void setidUnico(String idUnico) {
        this.idUnico = idUnico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


}
