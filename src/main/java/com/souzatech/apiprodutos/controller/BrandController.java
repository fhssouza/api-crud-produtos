package com.souzatech.apiprodutos.controller;

import com.souzatech.apiprodutos.model.Brand;
import com.souzatech.apiprodutos.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class BrandController {

    @Autowired
    private BrandService service;

    @GetMapping
    public ResponseEntity<List<Brand>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/por-nome")
    public ResponseEntity<Brand> findByNameIgnoreCase(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }

    @GetMapping("/listar-por-nome")
    public ResponseEntity<List<Brand>> findByListName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByListName(name));
    }

    @PostMapping
    public ResponseEntity<Brand> create(@RequestBody Brand entity, UriComponentsBuilder uri){
        return ResponseEntity.created(uri
                        .path("/marcas/{id}")
                        .buildAndExpand(entity.getId())
                        .toUri())
                .body(service.create(entity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Brand> update(@PathVariable Long id, @RequestBody Brand entity,
                                        UriComponentsBuilder uri){
        Brand brand = service.update(id, entity);
        return ResponseEntity.created(uri
                .path("/marcas/{id}")
                .buildAndExpand(entity.getId())
                .toUri())
                .body(brand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       service.deleteById(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
