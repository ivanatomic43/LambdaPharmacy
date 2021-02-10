package com.example.pharmacybackend.services;

import com.example.pharmacybackend.model.Offer;
import com.example.pharmacybackend.model.OrderItem;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;
import com.example.pharmacybackend.model.PharmacyMedicine;
import com.example.pharmacybackend.model.PurchaseOrder;
import com.example.pharmacybackend.model.Supplier;
import com.example.pharmacybackend.repository.OfferRepository;
import com.example.pharmacybackend.repository.OrderItemRepository;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
import com.example.pharmacybackend.repository.PharmacyMedicinesRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.PurchaseOrderRepository;
import com.example.pharmacybackend.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.example.pharmacybackend.dto.*;
import com.example.pharmacybackend.enumerations.MedicineStatus;
import com.example.pharmacybackend.enumerations.OfferStatus;
import com.example.pharmacybackend.enumerations.OrderStatus;

@Service
public class OrderService {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacyAdministratorRepository adminRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private PharmacyMedicinesRepository pharmacyMedicinesRepository;

    @Autowired
    private MedicineService medicineService;

    @Transactional
    public void savePurchase(PurchaseOrder po) {
        this.orderRepository.save(po);
    }

    public List<PurchaseOrderDTO> getAllOrders(Long id) {

        List<PurchaseOrderDTO> retList = new ArrayList<>();

        PharmacyAdministrator admin = adminRepository.findOneById(id);

        // my pharmacy
        List<Pharmacy> pharmList = pharmacyRepository.findAll();
        Pharmacy pharmacy = new Pharmacy();

        for (Pharmacy p : pharmList) {
            List<PharmacyAdministrator> admins = new ArrayList<>();
            admins = p.getPharmacyAdministrators();
            for (PharmacyAdministrator pa : admins) {
                if (pa.getId() == admin.getId()) {
                    pharmacy = p;

                    break;
                }
            }
        }

        // getting orders
        List<PurchaseOrder> orders = orderRepository.findAll();
        for (PurchaseOrder o : orders) {

            if (o.getPharmacy().getId() == pharmacy.getId()) {

                PurchaseOrderDTO newOrder = new PurchaseOrderDTO();
                newOrder.setId(o.getId());
                newOrder.setAdminID(o.getAdmin().getId());
                newOrder.setPharmacyID(o.getPharmacy().getId());
                newOrder.setName(o.getAdmin().getFirstName());
                newOrder.setSurname(o.getAdmin().getLastName());
                newOrder.setOrderDatee(o.getDate().toString());
                newOrder.setStatus(o.getStatus().toString());

                retList.add(newOrder);

            }

        }
        return retList;

    }

    public List<PurchaseOrderDTO> getAllOrdersSupplier() {

        List<PurchaseOrderDTO> retList = new ArrayList<>();

        List<PurchaseOrder> orders = orderRepository.findAll();

        for (PurchaseOrder po : orders) {
            if (!po.getStatus().equals(OrderStatus.FINISHED)) {
                PurchaseOrderDTO dto = new PurchaseOrderDTO();
                dto.setId(po.getId());
                dto.setOrderDatee(po.getDate().toString());
                dto.setAdminID(po.getAdmin().getId());
                dto.setPharmacyID(po.getPharmacy().getId());
                dto.setStatus(po.getStatus().toString());
                dto.setName(po.getAdmin().getFirstName());
                dto.setSurname(po.getAdmin().getLastName());
                dto.setPharmacyName(po.getPharmacy().getName());

                retList.add(dto);
            }
        }

        return retList;

    }

    @Transactional
    public boolean giveOffer(OfferDTO offer, Long id) {

        boolean done = false;
        Supplier supplier = supplierRepository.findOneById(id);

        List<PurchaseOrder> orders = orderRepository.findAll();
        for (PurchaseOrder po : orders) {
            if (po.getId() == offer.getOrderID()) {
                System.out.println("Pronadjena narudzbenica...");

                Offer newOffer = new Offer();
                newOffer.setDeliveryTime(offer.getDeliveryTime());
                newOffer.setTotalPrice(offer.getTotalPrice());
                newOffer.setPurchaseOrder(po);
                newOffer.setSupplier(supplier);
                newOffer.setStatus(OfferStatus.WAITING);

                offerRepository.save(newOffer);

                List<Offer> off = po.getOffers();
                off.add(newOffer);
                po.setStatus(OrderStatus.WAITING_FOR_ADMIN_APPROVAL);
                this.savePurchase(po);
                done = true;

            }
        }

        return done;
    }

    public PurchaseOrderDTO getOrderDetails(Long orderID) {

        PurchaseOrder order = orderRepository.findOneById(orderID);

        PurchaseOrderDTO dto = new PurchaseOrderDTO();

        dto.setId(order.getId());
        dto.setAdminID(order.getAdmin().getId());
        dto.setOrderDatee(order.getDate().toString());
        dto.setStatus(order.getStatus().toString());
        dto.setPharmacyID(order.getPharmacy().getId());
        dto.setName(order.getAdmin().getFirstName());
        dto.setSurname(order.getAdmin().getLastName());
        dto.setPharmacyName(order.getPharmacy().getName());

        return dto;
    }

    public List<MedicineDTO> getOrdersMedicines(Long orderID) {

        List<MedicineDTO> retList = new ArrayList<>();

        List<OrderItem> items = itemRepository.findAll();

        for (OrderItem ot : items) {
            if (ot.getPurchaseOrder().getId() == orderID) {

                MedicineDTO dto = new MedicineDTO();
                dto.setId(ot.getMedicine().getId());
                dto.setName(ot.getMedicine().getName());
                dto.setQuantity(ot.getQuantity());

                retList.add(dto);

            }
        }

        return retList;
    }

    public List<OfferDTO> getOffers(Long orderID) {

        List<OfferDTO> retList = new ArrayList<>();

        List<Offer> offers = offerRepository.findAll();

        for (Offer o : offers) {
            if (o.getPurchaseOrder().getId() == orderID) {

                OfferDTO dto = new OfferDTO();
                dto.setId(o.getId());
                dto.setDeliveryTimee(o.getDeliveryTime().toString());
                dto.setTotalPrice(o.getTotalPrice());
                dto.setSupplierName(o.getSupplier().getFirstName());
                dto.setSupplierSurname(o.getSupplier().getLastName());
                dto.setStatus(o.getStatus().toString());

                retList.add(dto);

            }

        }

        return retList;
    }

    @Transactional // updating medicine quantity in pharmacy
    public boolean acceptOffer(Long offerID, Long userID) {

        boolean accepted = false;

        Offer offer = offerRepository.findOneById(offerID);

        PurchaseOrder order = offer.getPurchaseOrder();

        PharmacyAdministrator admin = order.getAdmin();
        if (admin.getId() != userID) {
            System.out.println("Not your order, you can not accept it...");
            return accepted;
        }

        Pharmacy pharmacy = order.getPharmacy();

        // getting medicines from order
        List<OrderItem> items = itemRepository.findAll();
        List<OrderItem> myItems = new ArrayList<>();

        for (OrderItem ot : items) {
            if (ot.getPurchaseOrder().getId() == order.getId()) {

                myItems.add(ot);
            }
        }

        List<PharmacyMedicine> medicines = pharmacyMedicinesRepository.findAll();
        List<PharmacyMedicine> myMeds = new ArrayList<>();

        for (PharmacyMedicine pm : medicines) {
            if (pm.getPharmacy().getId() == pharmacy.getId()) {
                myMeds.add(pm);
            }
        }

        // checking if medicine exists in pharmacy

        if (!myMeds.isEmpty()) {
            for (OrderItem i : myItems) {
                for (PharmacyMedicine p : myMeds) {

                    if (p.getMedicine().getId() == i.getMedicine().getId()) {
                        /// lek pronadjen, azuriraj stanje

                        int quantity = p.getQuantity();
                        int newQ = quantity + i.getQuantity();
                        this.medicineService.updatePharmacyMedicineQuantity(newQ, p.getId());

                        this.medicineService.savePharmacyMedicine(p);
                        myItems.remove(i);

                    }

                }
            }

        } else {
            System.out.println("Pharm meds are empty..");
            // add all med from order;

            for (OrderItem it : myItems) {

                PharmacyMedicine newMed = new PharmacyMedicine();
                newMed.setMedicine(it.getMedicine());
                newMed.setQuantity(it.getQuantity());
                newMed.setPharmacy(pharmacy);

                newMed.setStatusInPharmacy(MedicineStatus.AVAILABLE);
                newMed.setPrice(0);

                Date date = new Date();

                newMed.setPriceLastsTo(date);

                pharmacyMedicinesRepository.save(newMed);
                break;
            }

        }

        if (!myItems.isEmpty()) { // adding medicines that don't exist in pharmacy
            for (OrderItem item : myItems) {

                PharmacyMedicine med = new PharmacyMedicine();
                med.setMedicine(item.getMedicine());
                med.setPharmacy(pharmacy);
                med.setQuantity(item.getQuantity());
                med.setPrice(0);
                Date date = new Date();
                med.setPriceLastsTo(date);
                med.setStatusInPharmacy(MedicineStatus.AVAILABLE);

                this.medicineService.savePharmacyMedicine(med);
            }
        }

        order.setStatus(OrderStatus.FINISHED);
        List<Offer> allOffers = offerRepository.findAll();
        for (Offer of : allOffers) {
            if (of.getId() != offerID) {
                of.setStatus(OfferStatus.DECLINED);
            }
        }

        offer.setStatus(OfferStatus.ACCEPTED);

        orderRepository.save(order);
        offerRepository.save(offer);
        accepted = true;

        return accepted;
    }

}
