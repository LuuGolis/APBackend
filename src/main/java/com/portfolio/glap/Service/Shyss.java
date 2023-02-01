/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.glap.Service;

import com.portfolio.glap.Entity.Hyss;
import com.portfolio.glap.Repository.Rhyss;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lugol
 */
@Transactional
@Service
public class Shyss {
    @Autowired
    Rhyss rhyss;
    
       public void save(Hyss skill){
        rhyss.save(skill);
    }
    
    public void delete(int id){
        rhyss.deleteById(id);
    }
    
    public List<Hyss> list(){
        return rhyss.findAll();
        
    }
    
    public boolean existsById(int id){
        return rhyss.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rhyss.existsByNombre(nombre);
    }
    
    public Optional<Hyss> getOne(int id){
        return rhyss.findById(id);
    }
    
    public Optional<Hyss> getByNombre(String nombre){
        return rhyss.findByNombre(nombre);
    }
    
 
    
    
    
}
