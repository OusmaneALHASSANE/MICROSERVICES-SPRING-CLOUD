package ousmane.alhassane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Data @AllArgsConstructor @NoArgsConstructor @ToString
class  Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String email;

}
@Projection(name = "fullCustomer",types = Customer.class)
interface CustomerProjection extends Projection{
    public Long getId();
    public String getName();
    public String getEmail();
}
@RepositoryRestResource
interface CustomerRepository extends JpaRepository<Customer,Long> { }

@SpringBootApplication
public class SpringCloudGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
          customerRepository.save(new Customer(null,"Hassan","hassan@gmail.com"));
          customerRepository.save(new Customer(null,"Mohamed","med@gmail.com"));
          customerRepository.save(new Customer(null,"Ousmane","ousmane@gmail.com"));
          customerRepository.save(new Customer(null,"Yahya","yahya@gmail.com"));
          customerRepository.findAll().forEach(System.out::println);

        };

    }

}
