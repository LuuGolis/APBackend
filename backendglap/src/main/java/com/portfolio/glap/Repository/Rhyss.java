
package com.portfolio.glap.Repository;

import com.portfolio.glap.Entity.Hyss;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Rhyss extends JpaRepository<Hyss, Integer>{
     Optional<Hyss> findByNombre(String nombre);
     public boolean existsByNombre(String nombre);
}
