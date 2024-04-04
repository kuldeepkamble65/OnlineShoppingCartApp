package org.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.Enum.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;


private String name;
private double price;

private int quantity;

@Enumerated(EnumType.STRING)
private Category category;

private String  expiryDate;


}
