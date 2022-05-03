package com.example.myreactspringbootrestapi.controller;

import com.example.myreactspringbootrestapi.dto.*;
import com.example.myreactspringbootrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public List<ProductDto> showProducts(@PathVariable long id) {
        return productService.getProductById(id).stream().map(ProductDtoConverter::toProductDto).toList();
    }

    @GetMapping("")
    public List<ProductDto> showProducts(@RequestParam Map<String, String> params) {
        if (params.containsKey("name")) {
            return productService.getProductByName(params.get("name")).stream().map(ProductDtoConverter::toProductDto).toList();
        }
        else {
            long page = params.containsKey("page") ? Long.parseLong(params.get("page")) : 0;
            long size = params.containsKey("size") ? Long.parseLong(params.get("size")) : 5;
            if (params.containsKey("genre")) {
                return productService.getProductsByGenre(page, size, params.get("genre")).stream().map(ProductDtoConverter::toProductDto).toList();
            }
            else {
                return productService.getProducts(page, size).stream().map(ProductDtoConverter::toProductDto).toList();
            }
        }
    }

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProduct(@RequestBody CreateProductDto createProductDto) {
        try {
            productService.createProduct(createProductDto.getName(), createProductDto.getGenre(), createProductDto.getQuantity(), createProductDto.getPrice(), createProductDto.getImg());
        }
        catch (Exception e) {
            return "error in creating product";
        }
        return "successfully create product";
    }

    @PutMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateProduct(@RequestBody UpdateProductDto updateProductDto) {
        try {
            productService.updateProduct(updateProductDto.getId(), updateProductDto.getName(), updateProductDto.getGenre(), updateProductDto.getQuantity(), updateProductDto.getPrice(), updateProductDto.getImg());
        }
        catch (Exception e) {
            return "error in updating product";
        }
        return "successfully update product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
        return "successfully delete product";
    }

    @DeleteMapping("")
    public String deleteProduct(@RequestParam(value ="name") String name) {
        productService.deleteProductByName(name);
        return "successfully delete product";
    }

    @GetMapping("/genres")
    public List<GenreDto> getGenres() {
        return productService.getProductGenres()
                .stream()
                .map((e)->{
                        return new GenreDto(e.toString());
                    }
                )
                .toList();
    }
}
