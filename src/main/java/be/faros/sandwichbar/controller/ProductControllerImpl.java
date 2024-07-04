package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Override
    public ModelAndView getAllProducts() {
        return new ModelAndView("products", "products", productService.findAllAvailableProducts());
    }

}
