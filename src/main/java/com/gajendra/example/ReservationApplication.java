package com.gajendra.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories("com.gajendra.example")
@EnableSpringDataWebSupport
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}
}

@Component
class StartupSeeder implements CommandLineRunner {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Gajendra", "Pooja", "Tanmay", "Mom")
               .forEach(s ->this.reservationRepository.save(new Reservation(s)));
    }
}

@RefreshScope
@RestController
class MessageController {
    @Value("Test")
    private String msg;

    @RequestMapping("msg")
    public String getMessage() {
        return this.msg;
    }
}
/*@RestController
class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/reservation")
    public Collection<Reservation> getResurvations() {
        return this.reservationRepository.findAll();
    }
}*/

@Entity
class Reservation {

    @Id
    @GeneratedValue
    private Long id;
    private String reservationName;

    public Reservation() {
    }

    public Reservation(String reservationName) {
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


