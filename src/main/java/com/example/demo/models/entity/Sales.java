package com.example.demo.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sale")
public class Sales implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "idSale")
    private Integer idSale;
    @Column(name = "idProducts")
    private Integer idProducts;
    @Column(name = "date")
    private String date;
    @Column(name = "finalPrice")
    private String finalPrice;

}
