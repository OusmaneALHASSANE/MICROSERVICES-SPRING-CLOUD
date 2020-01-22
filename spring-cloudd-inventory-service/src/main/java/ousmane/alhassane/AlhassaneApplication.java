package ousmane.alhassane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long> {

}
@Projection(name = "fullProduct",types = Product.class)
interface ProductProjection extends Projection{
    public Long getId();
    public String getName();
    public double getPrice();
}
@SpringBootApplication
public class AlhassaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlhassaneApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args->{
            productRepository.save(new Product(null,"Computer",6800));
            productRepository.save(new Product(null,"PHONE",9800));
            productRepository.save(new Product(null,"chair",800));
            productRepository.save(new Product(null,"BOOKS",640));
            productRepository.findAll().forEach(System.out::println);


        };
    }

}
