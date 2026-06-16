package com.sigat.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Titular;

public interface TitularRepository extends JpaRepository<Titular, Long> {

    Optional<Titular> findByCurp(String curp);

    boolean existsByCurp(String curp);

    List<Titular> findByNombresContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCase(
            String nombres, String apellido1, String apellido2);
}
