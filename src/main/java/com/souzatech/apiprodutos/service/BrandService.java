package com.souzatech.apiprodutos.service;

import com.souzatech.apiprodutos.model.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    List<Brand> findAll();
    Brand findById(Long id);
    Brand findByName(String name);
    List<Brand> findByListName(String name);
    Brand create(Brand entity);
    Brand update(Long id, Brand entity);
    void deleteById(Long id);

}
