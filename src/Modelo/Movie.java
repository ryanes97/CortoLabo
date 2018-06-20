/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LN710Q
 */
public class Movie {
    private int id;
    private String nombre;
    private String director;
    private String pais;
    private String calisificaion;
    private int anio;
    private boolean proyeccion;

    public Movie() {
    }

    
    public Movie(int id, String nombre, String director, String pais, String calisificaion, int anio, boolean proyeccion) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.calisificaion = calisificaion;
        this.anio = anio;
        this.proyeccion = proyeccion;
    }

    public Movie(String nombre, String director, String pais, String calisificaion, int anio, boolean proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
            this.calisificaion = calisificaion;
        this.anio = anio;
        this.proyeccion = proyeccion;
    }
    

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirector() {
        return director;
    }

    public String getPais() {
        return pais;
    }

    public String getCalisificaion() {
        return calisificaion;
    }

    public int getAnio() {
        return anio;
    }

    public boolean isProyeccion() {
        return proyeccion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCalisificaion(String calisificaion) {
        this.calisificaion = calisificaion;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setProyeccion(boolean proyeccion) {
        this.proyeccion = proyeccion;
    }
    
    
    
    
    
}
