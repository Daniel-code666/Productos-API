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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    
    /*
     * CREATE
     **/
    //ingresa una nueva imagen
    @PostMapping(value = "/save/{id}")
    public ResponseEntity<?> save(@PathVariable(value = "id")int postId,@RequestParam("file") MultipartFile file) throws IOException {
    	Imagen objImg=new Imagen();
    	Producto prod= new Producto();
    	prod.setIdProduct(postId);
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
    /*
     * UPDATE
     * */
    
    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") int imageId,@RequestParam("file") MultipartFile file) throws IOException {
        Optional<Imagen> oImage = (Optional<Imagen>) imgService.oneImage(imageId);
        if (!oImage.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
        	try {
        		//si no llega ninguna imagen, se elimina la anterior    o     que no cambie nada
        		if(file.isEmpty()) {
        			//imgService.deleteById(imageId);
                    //return ResponseEntity.ok().build();
        			return ResponseEntity.status(HttpStatus.CREATED).body(imgService.save(oImage.get()));
	        	}else {
	    			byte[] byteImg =file.getBytes();
	    			oImage.get().setImagen(byteImg);
	    			return ResponseEntity.status(HttpStatus.CREATED).body(imgService.save(oImage.get()));
        		}
    		} catch (IOException e) {
    			e.printStackTrace();
    			return ResponseEntity.notFound().build();
    		}	

            
        }

    }
    
    
    /*
     * READ
     * */
    
    //encuentra todas las imagenes 
    @GetMapping(value = "/findAll")
    public List<Imagen> readAll() {
        List<Imagen> imagen = imgService.imgAll();
        return imagen;
    }
    
    //encuentra las imagenes por el id de la publicacion
    @GetMapping(value = "/findByPost/{id}")
    public ResponseEntity<?> findByPublicacion (@PathVariable(value = "id")int ProductId){
    	
    	Producto p = new Producto();
    	p.setIdProduct(ProductId);
    	
    	List<Imagen> imagen= (List<Imagen>) imgService.imgByPublc(p);
    	
        if (imagen.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(imagen);
        }
    }
     //encuentra una imagen dependindo el id
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int imageId) {
        Optional<Imagen> oImage = (Optional<Imagen>) imgService.oneImage(imageId);
        if (!oImage.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(oImage);
        }
    }
    
    /*
     * DELETE
     * */
    
    //elimina todas las imagenes de una publicacion
    @DeleteMapping(value = "/deleteByPost/{id}")
    public ResponseEntity<?> deleteByPost (@PathVariable(value = "id") int postId){
    	Producto p = new Producto();
    	p.setIdProduct(postId);
    	List<Imagen> oProduct = (List<Imagen>) imgService.imgByPublc(p);
        if (oProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
        	imgService.deleteByProductId(p);
            return ResponseEntity.ok().build();
        }
    }
    //elimina una imagen dependiendo del id
    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") int imageId) {
    	Optional<Imagen> oImage = (Optional<Imagen>) imgService.oneImage(imageId);
        if (!oImage.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            imgService.deleteById(imageId);
            return ResponseEntity.ok().build();
        }
    }
    //elimina todas las imagenes....cuidado
    @DeleteMapping(value = "deleteAll")
    public ResponseEntity<?> deleteAll(){
    	List<Imagen> imagen = imgService.imgAll();
    	if (imagen.isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}else {
    		imgService.deleteAll();
            return ResponseEntity.ok().build();
    	}
    	
    }
    
}
