/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Daniel
 */
@Data
@Entity
@Table(name= "producto",catalog= "productos",schema = "")
public class Producto {
    private static final long serialVersionUID = 5504508647838076501L;
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String nombre;
	//otra posible descripcion de una tabla para el momento de crearla desde el api
	//@Column(name="descripcion",nullable=false,length = 200,unique=true)
	@Column
	private String descripcion;
	@Column
	private int precioUnidad;
	@Column
	private int stock;
}
