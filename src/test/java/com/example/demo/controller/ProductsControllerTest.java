package com.example.demo.controller;

import com.example.demo.models.entity.Products;
import com.example.demo.service.IProducts;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProducts productsService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public IProducts productsService() {
            return Mockito.mock(IProducts.class);
        }
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void testCreateProduct() throws Exception {
        Products product = new Products();
        product.setProductName("Laptop");
        product.setDescription("Gaming");
        product.setPrice(1200.00);

        Mockito.when(productsService.save(any(Products.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "productName": "Laptop",
                                    "description": "Gaming",
                                    "price": 1200.00
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Laptop"));
    }

    @Test
    @WithMockUser(username = "admin456", roles = {"USER"})
    void testFindAllProducts() throws Exception {
        List<Products> productList = Arrays.asList(
                new Products(1, "Product1", "Desc1", 10.0),
                new Products(2, "Product2", "Desc2", 20.0)
        );

        Mockito.when(productsService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}