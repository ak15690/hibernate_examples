package com.blacbuck.dealbreaker;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String state;

    private String addressData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    @JsonBackReference
    private List<Person> personList;

    public String getCity() {
        return city;
    }

    public String getAddressData() {
        return addressData;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddressData(String addressData) {
        this.addressData = addressData;
    }

    public List<Person> getPersonList() {
        return this.personList;
    }

    public void getPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
