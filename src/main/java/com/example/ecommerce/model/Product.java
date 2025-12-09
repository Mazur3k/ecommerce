package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int quantity;
    private double price;
    private double specialPrice;

    /*
        if no cascading there will be exception
        org.hibernate.TransientObjectException: persistent instance references an unsaved transient instance of 'com.example.ecommerce.model.Category'
        (save the transient instance before flushing)

        "Flushing is the process of synchronizing the state of the persistence context with the underlying database.
        The EntityManager and the Hibernate Session expose a set of methods,
        through which the application developer can change the persistent state of an entity."

        When we have assosiated persistent object(entity field) we can have it in transient state when flushing session

        "Persistence context keeps track of any changes made into a managed entity.
        If anything changes during a transaction, then the entity is marked as dirty.
        When the transaction completes, these changes are flushed into persistent storage."
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
