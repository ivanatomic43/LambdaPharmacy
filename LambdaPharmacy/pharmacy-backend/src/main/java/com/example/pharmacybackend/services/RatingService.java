package com.example.pharmacybackend.services;

import java.util.List;

import com.example.pharmacybackend.enumerations.RateStatus;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.Medicine;
import com.example.pharmacybackend.model.Pharmacist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.Rating;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.repository.DermatologistRepository;
import com.example.pharmacybackend.repository.MedicineRepository;
import com.example.pharmacybackend.repository.PharmacistRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineService medicineService;

    public Rating save(Rating r) {
        return this.ratingRepository.save(r);
    }

    public List<Integer> dermatologistRates(Long dermID) {
        return ratingRepository.dermatologistRates(dermID);
    }

    public List<Integer> pharmacistRates(Long pharmID) {
        return ratingRepository.pharmacistRates(pharmID);
    }

    public List<Integer> pharmacyRates(Long pharmacyID) {
        return ratingRepository.pharmacyRates(pharmacyID);
    }

    public List<Integer> medicineRates(Long medicineID) {
        return ratingRepository.medicineRates(medicineID);
    }

    public double rateDermatologist(Long id, Integer rate, User user) {

        Dermatologist dermatologist = dermatologistRepository.findOneById(id);

        Rating rateModel = new Rating();
        rateModel.setRate(rate);
        rateModel.setUser(user);
        rateModel.setDermatologist(dermatologist);
        rateModel.setRateStatus(RateStatus.RATED);
        this.save(rateModel);

        List<Integer> rates = this.dermatologistRates(id);
        int zbir = 0;
        for (int i = 0; i < rates.size(); i++)
            zbir += rates.get(i);

        double rating = (double) zbir / rates.size();

        dermatologistService.updateDermatologistRating(id, rating);
        return rating;
    }

    public double changeRateDermatologist(Long id, Integer rate, User user) {

        List<Rating> allRates = ratingRepository.findAll();

        for (Rating r : allRates) {

            if (r.getUser().getId() == user.getId() && r.getDermatologist().getId() == id) {

                r.setRate(rate);
                this.save(r);

                List<Integer> rates = this.dermatologistRates(id);
                int zbir = 0;
                for (int i = 0; i < rates.size(); i++)
                    zbir += rates.get(i);

                double rating = (double) zbir / rates.size();

                dermatologistService.updateDermatologistRating(id, rating);
                return rating;

            }

        }
        return 0;
    }

    public double ratePharmacist(Long id, Integer rate, User user) {

        Pharmacist pharmacist = pharmacistRepository.findOneById(id);

        Rating rateModel = new Rating();
        rateModel.setRate(rate);
        rateModel.setUser(user);
        rateModel.setPharmacist(pharmacist);
        this.save(rateModel);

        List<Integer> rates = this.pharmacistRates(id);
        int zbir = 0;
        for (int i = 0; i < rates.size(); i++)
            zbir += rates.get(i);

        double rating = (double) zbir / rates.size();

        pharmacistService.updatePharmacistRating(id, rate);

        return rating;

    }

    public double changeRatePharmacist(Long id, Integer rate, User user) {

        List<Rating> allRates = ratingRepository.findAll();

        for (Rating r : allRates) {
            if (r.getUser().getId() == user.getId() && r.getPharmacist().getId() == id) {

                r.setRate(rate);
                this.save(r);

                List<Integer> rates = this.pharmacistRates(id);
                int zbir = 0;
                for (int i = 0; i < rates.size(); i++)
                    zbir += rates.get(i);

                double rating = (double) zbir / rates.size();

                pharmacistService.updatePharmacistRating(id, rating);
                return rating;

            }

        }
        return 0;
    }

    public double ratePharmacy(Long id, Integer rate, User user) {

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        Rating rateModel = new Rating();
        rateModel.setRate(rate);
        rateModel.setUser(user);
        rateModel.setPharmacy(pharmacy);
        this.save(rateModel);

        List<Integer> rates = this.pharmacyRates(id);
        int zbir = 0;
        for (int i = 0; i < rates.size(); i++)
            zbir += rates.get(i);

        double rating = (double) zbir / rates.size();

        pharmacyService.updatePharmacyRating(id, rate);

        return rating;

    }

    public double changeRatePharmacy(Long id, Integer rate, User user) {

        List<Rating> allRates = ratingRepository.findAll();

        for (Rating r : allRates) {
            if (r.getUser().getId() == user.getId() && r.getPharmacy().getId() == id) {

                r.setRate(rate);
                this.save(r);

                List<Integer> rates = this.pharmacyRates(id);
                int zbir = 0;
                for (int i = 0; i < rates.size(); i++)
                    zbir += rates.get(i);

                double rating = (double) zbir / rates.size();

                pharmacyService.updatePharmacyRating(id, rating);
                return rating;

            }

        }
        return 0;
    }

    public double rateMedicine(Long id, Integer rate, User user) {

        Medicine medicine = medicineRepository.findOneById(id);

        Rating rateModel = new Rating();
        rateModel.setRate(rate);
        rateModel.setUser(user);
        rateModel.setMedicine(medicine);
        this.save(rateModel);

        List<Integer> rates = this.medicineRates(id);
        int zbir = 0;
        for (int i = 0; i < rates.size(); i++)
            zbir += rates.get(i);

        double rating = (double) zbir / rates.size();

        medicineService.updateMedicineRating(id, rate);

        return rating;

    }

    public double changeRateMedicine(Long id, Integer rate, User user) {

        List<Rating> allRates = ratingRepository.findAll();

        for (Rating r : allRates) {
            if (r.getUser().getId() == user.getId() && r.getMedicine().getId() == id) {

                r.setRate(rate);
                this.save(r);

                List<Integer> rates = this.medicineRates(id);
                int zbir = 0;
                for (int i = 0; i < rates.size(); i++)
                    zbir += rates.get(i);

                double rating = (double) zbir / rates.size();

                medicineService.updateMedicineRating(id, rating);
                return rating;

            }

        }
        return 0;
    }

}
