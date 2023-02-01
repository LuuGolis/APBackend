/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.glap.Controller;

import com.portfolio.glap.Dto.dtoHyss;
import com.portfolio.glap.Entity.Hyss;
import com.portfolio.glap.Security.Controller.Mensaje;
import com.portfolio.glap.Service.Shyss;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://frontendglap.web.app"})
@RequestMapping("/hyss")
public class Chyss {
    @Autowired
    Shyss shyss;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Hyss>> list(){
        List<Hyss> list = shyss.list();
        return new ResponseEntity(list, HttpStatus.OK);
        
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hyss> getById(@PathVariable("id") int id){
        if(!shyss.existsById(id))
            return new ResponseEntity(new Mensaje("El ID indicado no existe"), HttpStatus.NOT_FOUND);
        Hyss hys = shyss.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!shyss.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe ID indicado"), HttpStatus.NOT_FOUND);
        }
        shyss.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada correctamente"), HttpStatus.OK);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyss dtohyss){
    if(StringUtils.isBlank(dtohyss.getNombre()))
        return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"), HttpStatus.BAD_REQUEST);
   
    if(shyss.existsByNombre(dtohyss.getNombre()))
        return new ResponseEntity(new Mensaje("La skill ya existe"), HttpStatus.BAD_REQUEST);
    
    Hyss hys = new Hyss(dtohyss.getNombre(), dtohyss.getPorcentaje());
    shyss.save(hys);
    
    return new ResponseEntity(new Mensaje("Skill agregada correctamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyss dtohyss){
       //valido si existe el id indicado
        if(!shyss.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //comparo nombre de skills
        if(shyss.existsByNombre(dtohyss.getNombre())&& shyss.getByNombre(dtohyss.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);
        //indico que el nombre no puede ser null
        if(StringUtils.isBlank(dtohyss.getNombre()))
            return new ResponseEntity(new Mensaje("Indicar nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        //lo mismo que arriba pero para %
        // OJO: PORCENTAJE ES INETEGR, HAY QUE ENCONTRAR OTRA FORMA QUE LO VALIDE
        // SIN QUE SEA STRING UTILS
        //if(StringUtils.isBlank(dtohyss.getPorcentaje()))
           // return new ResponseEntity(new Mensaje("Indicar porcentaje es obligatorio"), HttpStaus.BAD_REQUEST);
        
        Hyss hys = shyss.getOne(id).get();
        hys.setNombre(dtohyss.getNombre());
        hys.setPorcentaje((dtohyss.getPorcentaje()));
        
        shyss.save(hys);
        return new ResponseEntity(new Mensaje("Skill correctamente actualizada"), HttpStatus.OK);
        
    }
    
   
    
}
