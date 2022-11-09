/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.controllers;

import com.products.products.models.Imagen;
import com.products.products.models.Producto;
import com.products.products.service.ImagenService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping(value = "/imagen")
public class ImagenContoller {
    @Autowired
    private ImagenService imgService;
    
    @GetMapping(value = "/findAll")
    public List<Imagen> readAll() {
        List<Imagen> imagen = imgService.imgAll();
        return imagen;
    }
    @GetMapping(value = "/findByProduct/{id}")
    public ResponseEntity<?> findByPublicacion (@PathVariable(value = "id")int ProductId){
    	
    	List<Imagen> imagen= (List<Imagen>) imgService.imgByPublc(ProductId);
    	
        if (imagen.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(imagen);
        }
    }
    
    
    @PostMapping(value = "/save/{id}")
    public ResponseEntity<?> save(@PathVariable(value = "id")int postId,@RequestParam("file") MultipartFile file) throws IOException {
    	Imagen objImg=new Imagen();
    	Producto prod= new Producto();
    	prod.setId(postId);
    	try {    		
			byte[] byteImg =file.getBytes();
			objImg.setProducto(prod);
			objImg.setImagen(byteImg);
			return ResponseEntity.status(HttpStatus.CREATED).body(imgService.save(objImg));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
    	
    	
    }
}
