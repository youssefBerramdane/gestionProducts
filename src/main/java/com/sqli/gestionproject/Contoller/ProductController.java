package com.sqli.gestionproject.Contoller;

import com.sqli.gestionproject.Dto.ProductDto;
import com.sqli.gestionproject.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }
    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/{code}")
    public ProductDto findByCode(@PathVariable String code){
        return productService.findByCode(code);
    }

    @PutMapping("/{code}")
    public ProductDto update(@PathVariable String code, @RequestBody ProductDto productDto) {
        return productService.update(code, productDto);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        productService.deleteById(code);
    }
}
