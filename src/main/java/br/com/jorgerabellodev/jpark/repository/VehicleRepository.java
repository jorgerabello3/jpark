package br.com.jorgerabellodev.jpark.repository;

import br.com.jorgerabellodev.jpark.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
