package trancongminh_20121801.lap07;

import net.datafaker.Faker;
import net.datafaker.providers.base.Address;
import net.datafaker.providers.base.Device;
import net.datafaker.providers.base.Name;
import net.datafaker.providers.base.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import trancongminh_20121801.lap07.enums.EmployeeStatus;
import trancongminh_20121801.lap07.enums.ProductStatus;
import trancongminh_20121801.lap07.models.*;
import trancongminh_20121801.lap07.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class Lap07Application {



    public static void main(String[] args) {

        SpringApplication.run(Lap07Application.class, args);


    }
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;
    @Autowired
    OrderRepository orderRepository;
 //  @Bean
    CommandLineRunner createSampleProduct(OrderDetailRepository orderDetailRepository){
        return args -> {
            Faker faker = new Faker();
            Device device =faker.device();
            Number number =faker.number();
            Name name = faker.name();
            Address address = faker.address();
            Random random = new Random();

            for(int i=0;i<100;i++){
                Product product = new Product(
                        device.modelName(),
                        faker.lorem().sentence(5),
                        "piece",
                        device.manufacturer(),
                        ProductStatus.ACTIVE
                );
                productRepository.save(product);
                ProductPrice productPrice = new ProductPrice(
                        product,
                        LocalDateTime.now(),
                        number.randomDouble(2,100,1000),
                        ""
                );
                productPriceRepository.save(productPrice);
                Customer customer = new Customer(
                        name.name(),
                        "cust_"+i+"@gmail.com",
                        random.nextInt(999999999+1-111111111)+111111111+"",
                        address.fullAddress()
                );
                customerRepository.save(customer);
                Employee employee = new Employee(
                        name.name(),
                        LocalDate.now(),
                        "emp_"+i+"@gmail.com",
                        random.nextInt(999999999+1-111111111)+111111111+"",
                        address.fullAddress(),
                        EmployeeStatus.ACTIVE
                );
                employeeRepository.save(employee);
                Order order = new Order(
                        LocalDateTime.now(),
                        employee,
                        customer
                );
                orderRepository.save(order);
                OrderDetail orderDetail = new OrderDetail(
                        random.nextInt(3),
                        number.randomDouble(2,100,1000),
                        "",
                        order,
                        product);
                orderDetailRepository.save(orderDetail);

            }
        };
    }

}
