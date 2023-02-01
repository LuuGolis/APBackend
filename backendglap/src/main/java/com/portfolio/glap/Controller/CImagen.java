
package com.portfolio.glap.Controller;

import Util.ImageUtility;
import com.portfolio.glap.Entity.Imagen;
import com.portfolio.glap.Repository.RImagen;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:8090")
public class CImagen {
    @Autowired
    RImagen rImagen;
    /*
    
   @PostMapping("/upload/image")
   public ResponseEntity<ImgUplRpta> uploadImage(@RequestParam("image") MultipartFile file)
           throws IOException {
       RImagen.save(Imagen.builder()
       .name(file.getOriginalFilename())
       .type(file.getContentType())
       .imagen(ImageUtility.compressImage(file.getBytes())).build());
       return ResponseEntity.status(HttpStatus.OK)
               .body(new ImgUplRpta("Imagen subida correctamente c: " + file.getOriginalFilename()));
   }
    */
}
