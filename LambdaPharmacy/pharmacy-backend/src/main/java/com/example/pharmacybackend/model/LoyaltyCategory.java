package com.example.pharmacybackend.model;

import java.util.List;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.CategoryType;

@Entity
@Table(name = "loyalty_category")
public class LoyaltyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @Column
    private int discount;

    @Column
    private int pointsBorder;

    @OneToMany(mappedBy = "loyaltyCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> userList;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return CategoryType return the type
     */
    public CategoryType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(CategoryType type) {
        this.type = type;
    }

    /**
     * @return int return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return List<User> return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return int return the pointsBorder
     */
    public int getPointsBorder() {
        return pointsBorder;
    }

    /**
     * @param pointsBorder the pointsBorder to set
     */
    public void setPointsBorder(int pointsBorder) {
        this.pointsBorder = pointsBorder;
    }

}
