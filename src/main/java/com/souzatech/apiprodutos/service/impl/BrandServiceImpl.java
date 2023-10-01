package com.souzatech.apiprodutos.service.impl;

import com.souzatech.apiprodutos.exception.EntityActiveInactive;
import com.souzatech.apiprodutos.exception.NotFoundException;
import com.souzatech.apiprodutos.model.Brand;
import com.souzatech.apiprodutos.repository.BrandRepository;
import com.souzatech.apiprodutos.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    public static final String MSG_MARCA_NAO_ENCONTRADO = "Cadastro de Marca não encontrado";
    private static final String MSG_MARCA_EM_USO = "Marca não pode ser excluida, pois está em uso";
    private static final String MSG_MARCA_ATIVA = "A Marca já está Ativa";
    private static final String MSG_MARCA_INATIVA = "A Marca está inativa";


    @Autowired
    private BrandRepository repository;
    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public Brand findById(Long id) {

        return getBrandId(id);
    }
    @Override
    public Brand findByName(String name) {
        return getBrandName(name);
    }

    @Override
    public List<Brand> findByListName(String name) {
        return getBrandListName(name);
    }

    @Override
    public Brand create(Brand entity) {
        return repository.save(entity);
    }

    @Override
    public Brand update(Long id, Brand entity) {
        Brand brand = getBrandId(id);
        brand.setName(entity.getName());
        return repository.save(brand);
    }

    @Override
    public Brand activeById(Long id) {
        Brand brand = getBrandIdActive(id);
        brand.setActive(true);
        return repository.save(brand);
    }

    @Override
    public Brand inactiveById(Long id) {
        Brand brand = getBrandId(id);
        brand.setActive(false);
        return repository.save(brand);
    }

    @Override
    public void deleteById(Long id) {
        getBrandId(id);
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException(
                    String.format(MSG_MARCA_NAO_ENCONTRADO));
        }catch (DataIntegrityViolationException e){
            throw new com.souzatech.apiprodutos.exception.DataIntegrityViolationException(
                    String.format(MSG_MARCA_EM_USO));
        }
    }

    private Brand getBrandId(Long id) {
        Optional<Brand> brand = repository.findById(id);
        if(brand.isEmpty()){
            throw  new NotFoundException(
                    String.format(MSG_MARCA_NAO_ENCONTRADO));
        }else if(brand.get().getActive().equals(false)){
            throw  new EntityActiveInactive(
                    String.format(MSG_MARCA_INATIVA));
        }
        return brand.get();
    }

    private Brand getBrandIdActive(Long id) {
        Optional<Brand> brand = repository.findById(id);
        if(brand.isEmpty()){
            throw  new NotFoundException(
                    String.format(MSG_MARCA_NAO_ENCONTRADO));
        }else if(!brand.get().getActive().equals(false)){
            throw  new EntityActiveInactive(
                    String.format(MSG_MARCA_ATIVA));
        }
        return brand.get();
    }

    private Brand getBrandName(String name) {
        Optional<Brand> brand = repository.findByNameIgnoreCase(name);
        if(brand.isEmpty()){
            throw  new NotFoundException(
                    String.format(MSG_MARCA_NAO_ENCONTRADO));
        }else if(brand.get().getActive().equals(false)){
            throw  new EntityActiveInactive(
                    String.format(MSG_MARCA_INATIVA));
        }
        return brand.get();
    }

    private List<Brand> getBrandListName(String name) {
        List<Brand> brands = repository.findByNameContainingIgnoreCase(name);

        if(brands.isEmpty()){
            throw new NotFoundException(
                    String.format(MSG_MARCA_NAO_ENCONTRADO));
        }

        List<Brand> novaList = new ArrayList<>();

        for (Brand list : brands) {
            if(list.getActive().equals(true)){
                novaList.add(list);
            }
        }

        return novaList;
    }

}
