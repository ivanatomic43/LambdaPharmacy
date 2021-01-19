package com.example.pharmacybackend.dto;

public class SearchUserDTO {

    private String searchName;
    private String searchSurname;

    public SearchUserDTO() {
    }

    /**
     * @return String return the searchName
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName the searchName to set
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * @return String return the searchSurname
     */
    public String getSearchSurname() {
        return searchSurname;
    }

    /**
     * @param searchSurname the searchSurname to set
     */
    public void setSearchSurname(String searchSurname) {
        this.searchSurname = searchSurname;
    }

}
