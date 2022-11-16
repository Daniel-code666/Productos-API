/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Daniel
 */
@Data
@Entity
@Table(name = "imagenes",catalog = "productos",schema = "")
public class Imagen {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImagen;
    @Column
    private byte[] imagen;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "idProduct")
    private Producto producto;
    
    //comment
}
