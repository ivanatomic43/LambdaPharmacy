package com.example.pharmacybackend.services;

import com.example.pharmacybackend.model.Image;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void save(Image img) {
        this.imageRepository.save(img);
    }

    public Image getImage(Pharmacy p) {
        System.out.println("Usao u servis image" + p.toString());
        return this.imageRepository.getByPharmacy(p);
    }

}
