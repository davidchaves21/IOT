package com.iot.back;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iot.back.models.entities.Product;
import com.iot.back.repositories.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
    ProductRepository productRepository;

    

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = Arrays.asList(
                new Product(null, "Skol Beats", 15.5, 10, "https://d2r9epyceweg5n.cloudfront.net/stores/001/043/122/products/skol-beats-senses-269-ml1-15d45339a53a21d4d315676906626426-640-0.jpg"),
                new Product(null, "TNT Energy Drink Beats", 5.5, 35, "https://images.tcdn.com.br/img/img_prod/887622/energetico_tnt_energi_drin_269ml_2339_1_20201124112052.jpg"),
                new Product(null, "Cerveja Budweiser American Lager", 4.65, 100, "https://static.paodeacucar.com/img/uploads/1/494/693494.jpg"),
                new Product(null, "Whisky Jack Daniels 1000 Ml", 100.00, 50, "https://m.media-amazon.com/images/I/61+XosyvBcL.jpg")
        );
        
        for (Product product : products) {
            if (!productRepository.findByName(product.getName()).isPresent()) {
                productRepository.save(product);
            }
        }
    }
}