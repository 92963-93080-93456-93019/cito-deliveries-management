package ua.tqs.cito;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findById(Long l);
    public List<Product> findAll();
    public List<Product> findByAppId(Long l);

}
