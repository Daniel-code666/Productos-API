/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.serviceimpl;

import com.products.products.repository.InterfaceImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.products.products.models.Imagen;
import com.products.products.models.Producto;
import com.products.products.service.ImagenService;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Daniel
 */
@Service
public class ImagenServiceImpl implements ImagenService{
    @Autowired
    private InterfaceImagen img;

    @Override
    public List<?> imgByPublc(Producto p) {
        return img.findByProducto(p);
    }

    @Override
    public List<Imagen> imgAll() {
        return img.findAll();
    }

    @Override
    public Optional<?> oneImage(int id) {
        return img.findById(id);
    }

    @Override
    public Imagen save(Imagen image) {
    	
        return img.save(image);
    }

    @Override
    public void deleteById(int id) {
        img.deleteById(id);
    }

	@Override
	@Transactional
	public void deleteByProductId(Producto p) {
		img.deleteByProducto(p);
		
	}

	@Override
	public void deleteAll() {
		img.deleteAll();
	}

	
}
