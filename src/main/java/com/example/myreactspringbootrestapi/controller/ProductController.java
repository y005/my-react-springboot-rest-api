package com.example.myreactspringbootrestapi.controller;

import com.example.myreactspringbootrestapi.domain.Genre;
import com.example.myreactspringbootrestapi.domain.Product;
import com.example.myreactspringbootrestapi.dto.CreateProductDto;
import com.example.myreactspringbootrestapi.dto.GenreDto;
import com.example.myreactspringbootrestapi.dto.ProductDto;
import com.example.myreactspringbootrestapi.dto.ProductDtoConverter;
import com.example.myreactspringbootrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<ProductDto> showProducts(@PathVariable long id) {
        return productService.getProductById(id).stream().map(ProductDtoConverter::toProductDto).toList();
    }

    @GetMapping("")
    @ResponseBody
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
    @ResponseBody
    public String createProduct(@RequestBody CreateProductDto createProductDto) {
        try {
            productService.createProduct(createProductDto.getName(), createProductDto.getGenre(), createProductDto.getQuantity(), createProductDto.getPrice(), createProductDto.getImg());
        } catch (Exception e) {
            return "error in creating product";
        }
        return "successfully create product";
    }

    @PutMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateProduct(@RequestBody CreateProductDto createProductDto) {
        try {
            productService.updateProduct(createProductDto.getName(), createProductDto.getGenre(), createProductDto.getQuantity(), createProductDto.getPrice(), createProductDto.getImg());
        } catch (Exception e) {
            return "error in creating product";
        }
        return "successfully create product";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
        return "successfully delete product";
    }

    @DeleteMapping("")
    @ResponseBody
    public String deleteProduct(@RequestParam(value ="name") String name) {
        productService.deleteProductByName(name);
        return "successfully delete product";
    }

    @GetMapping("/genres")
    @ResponseBody
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
