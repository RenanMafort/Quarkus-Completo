package com.sereia.renan.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    @ManyToOne
    public Restaurante restaurante;

    public BigDecimal preco;

}
