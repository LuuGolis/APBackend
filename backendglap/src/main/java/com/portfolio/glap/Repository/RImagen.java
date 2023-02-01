
package com.portfolio.glap.Repository;

import com.portfolio.glap.Entity.Imagen;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RImagen extends JpaRepository<Imagen, Long>{
    Optional<Imagen> findByName(String name);
}
