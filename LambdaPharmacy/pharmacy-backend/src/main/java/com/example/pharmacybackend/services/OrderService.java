package com.example.pharmacybackend.services;

import com.example.pharmacybackend.model.Offer;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;
import com.example.pharmacybackend.model.PurchaseOrder;
import com.example.pharmacybackend.model.Supplier;
import com.example.pharmacybackend.repository.OfferRepository;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.PurchaseOrderRepository;
import com.example.pharmacybackend.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import com.example.pharmacybackend.dto.*;
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
                    System.out.println("pharmacydi" + pharmacy.getId());
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
                System.out.println("STIGAO OVDE:" + newOrder.getId());
                retList.add(newOrder);

            }

        }
        return retList;

    }

    public List<PurchaseOrderDTO> getAllOrdersSupplier() {

        List<PurchaseOrderDTO> retList = new ArrayList<>();

        List<PurchaseOrder> orders = orderRepository.findAll();

        for (PurchaseOrder po : orders) {

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

}
