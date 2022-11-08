/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.controllers;

import com.products.products.models.Imagen;
import com.products.products.service.ImagenService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
