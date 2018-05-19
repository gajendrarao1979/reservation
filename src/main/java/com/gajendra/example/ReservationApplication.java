package com.gajendra.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories("com.gajendra.example")
@EnableSpringDataWebSupport
@EnableDiscoveryClient
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

    @Bean public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

@Component
class StartupSeeder implements CommandLineRunner {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Gajendra", "Pooja", "Tanmay", "Mom")
               .forEach(s ->this.reservationRepository.save(new MyReservation(s)));
    }
}

@RefreshScope
@RestController
class MessageController {
    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Value("Test")
    private String msg;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("msg")
    public String getMessage() {
        log.info("Message Request called");
        return this.msg;
    }

    @RequestMapping("/msg-another") public String getAnotherMessage() {
        log.info("Message another Request called");
        return restTemplate.getForObject("http://localhost:8000/msg", String.class);
    }
}

/*@RestController
class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/reservation")
    public Collection<MyReservation> getResurvations() {
        return this.reservationRepository.findAll();
    }
}*/

@Entity
class MyReservation {

    @Id
    @GeneratedValue
    private Long id;
    private String reservationName;

    public MyReservation() {
    }

    public MyReservation(String reservationName) {
        this.reservationName = reservationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }
}


