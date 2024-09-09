package br.com.jorgerabellodev.jpark.repository;

import br.com.jorgerabellodev.jpark.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
