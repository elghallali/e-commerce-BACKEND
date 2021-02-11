package com.elghallali.ecommercebackend.entity;

import javax.persistence.*;

@Entity
public class CheckoutCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String order_id;
    private String payment_type;
    private String delivering_address;
    private Long user_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double price;
    @Column(updatable=false, insertable=false)
    private String order_date;

}
