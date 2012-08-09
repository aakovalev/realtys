package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Konstantin
 */
@Entity
@Table(name="deliveryOrder")
public class DeliveryOrder implements Serializable {

    private static final long serialVersionUID = -7565191637650400857L;

	@Id
	@Column(name = "deliveryOrderId")
	private Integer deliveryOrderId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    @Column(name="rooms")
    Integer rooms;

    @Column(name="price")
    Integer price;

    @Column(name = "floor")
    Integer floor;

    @Column(name="maxFloor")
    Integer maxFloor;

    @Lob
    @Column(name="text")
    String text;

    @Column(name="period")
    Integer period;

    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    @Column(name="address")
    String address;

    @Column(name="phone")
    String phone;

    @ManyToOne(targetEntity = District.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    District district;

    @OneToMany (mappedBy = "deliveryOrder",
            fetch = FetchType.EAGER)
    private List<DeliveryImage> images;

    public Integer getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDeliveryOrderId(Integer deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<DeliveryImage> getImages() {
        return images;
    }

    public void setImages(List<DeliveryImage> images) {
        this.images = images;
    }
}
