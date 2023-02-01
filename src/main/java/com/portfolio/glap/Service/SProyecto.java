/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.glap.Service;

import com.portfolio.glap.Entity.Proyecto;
import com.portfolio.glap.Repository.RProyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lugol
 */
@Service
@Transactional
public class SProyecto {
    @Autowired
    RProyecto rProyecto;
    
    public List<Proyecto> list(){
        return rProyecto.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return rProyecto.findById(id);
    }
    
    public Optional<Proyecto> getByNombreP(String nombreP){
        return rProyecto.findByNombreP(nombreP);
    }
    
    public void save(Proyecto proyect){
        rProyecto.save(proyect);
    }
    
    public void delete(int id){
        rProyecto.deleteById(id);
    }
    
    public boolean existsByNombreP( String nombreP){
        return rProyecto.existsByNombreP(nombreP);
       
    }
    
    public boolean existsById(int id){
        return rProyecto.existsById(id);
    }
}
