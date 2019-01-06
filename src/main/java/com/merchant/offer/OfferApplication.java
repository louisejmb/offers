package com.merchant.offer;

import com.merchant.offer.domain.Offer;
import com.merchant.offer.domain.Product;
import com.merchant.offer.repository.OfferRepository;
import com.merchant.offer.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class OfferApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(OfferApplication.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OfferRepository offerRepository;

	public static void main(String[] args) {
		SpringApplication.run(OfferApplication.class, args);
	}

	public void run (String ... strings) throws Exception {
		Product pencilSharpener = new Product("Pencil sharpener", "GBP", 200);
		productRepository.save(pencilSharpener);

		Product garlicCrusher = new Product("Garlic crusher", "GBP", 140);
        productRepository.save(garlicCrusher);

        Product ripeApples = new Product("Ripe apples", "GBP", 100);
        productRepository.save(ripeApples);

        productRepository.save(new Product(
                "Origami unicorn", "GBP", 11000));
		productRepository.save(new Product("Unripe bananas", "GBP", 199));
		productRepository.save(new Product("Guitar tuner", "GBP", 1299));

		offerRepository.save(
				new Offer(
						pencilSharpener,
						"Half price pencil sharpeners for one week.",
						"GBP",
						100,
						LocalDate.of(2019, Month.JANUARY, 1),
						LocalDate.of(2019, Month.JANUARY, 8)
                )
		);

		offerRepository.save(
				new Offer(
						ripeApples,
						"20% off ripe apples in January.",
						"GBP",
						80,
						LocalDate.of(2019, Month.JANUARY, 1),
						LocalDate.of(2019, Month.JANUARY, 31)
				)
		);

		offerRepository.save(
				new Offer(
						garlicCrusher,
						"Free garlic crusher for today only.",
						"GBP",
						0,
						LocalDate.of(2019, Month.JANUARY, 8),
						LocalDate.of(2019, Month.JANUARY, 8)
				)
		);

		offerRepository.save(
				new Offer(
						pencilSharpener,
						"25% off pencil sharpeners for March.",
						"GBP",
						150,
						LocalDate.of(2019, Month.MARCH, 1),
						LocalDate.of(2019, Month.MARCH, 31)
				)
		);
	}

}