/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.glap.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lugol
 */

@Entity
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   
    private String nombreE;
    private String descripcionE;
    private String anioE;
    
    //constructores :3

    public Experiencia() {
    }

    public Experiencia(String nombreE, String descripcionE, String anioE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.anioE = anioE;
    }
    
    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }
   

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
    
     public String getAnioE() {
        return anioE;
    }

    public void setAnioE(String anioE) {
        this.anioE = anioE;
    }
    
    
    
}
