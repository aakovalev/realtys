package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
@Entity
@Table(name = "rentOrder")
public class RentOrder implements Serializable {

    private static final long serialVersionUID = 8716962750072249098L;

	@Id
	@Column(name = "rentOrderId")
	private Integer rentOrderId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    @Column(name="rooms")
    Integer rooms;

    @Column(name="minPrice")
    Integer minPrice;

    @Column(name="maxPrice")
    Integer maxPrice;

    @Lob
    @Column(name="text")
    String text;

    @Column(name="period")
    Integer period;

    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    @Lob
    @Column(name="who")
    String who;

    @Column(name="phone")
    String phone;

    @ManyToOne(targetEntity = District.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    District district;

    @OneToMany (mappedBy = "rentOrder",
            fetch = FetchType.EAGER)
    private List<RentImage> images;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(Integer rentOrderId) {
        this.rentOrderId = rentOrderId;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String sho) {
        this.who = sho;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<RentImage> getImages() {
        return images;
    }

    public void setImages(List<RentImage> images) {
        this.images = images;
    }
}
