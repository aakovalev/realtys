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
@Table(name="evaluationOrder")
public class EvaluationOrder implements Serializable{

    private static final long serialVersionUID = -3923672708778890127L;

	@Id
	@Column(name = "evaluationOrderId")
	private Integer evaluationOrderId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    @Column(name="rooms")
    Integer rooms;

    @Column(name = "floor")
    Integer floor;

    @Column(name="maxFloor")
    Integer maxFloor;

    @Lob
    @Column(name="text")
    String text;

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

    @ManyToOne(targetEntity = EstateCategory.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    EstateCategory estateCategory;

    @OneToMany (mappedBy = "evaluationOrder",
            fetch = FetchType.EAGER)
    private List<EvaluationImage> images;

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

    public Integer getEvaluationOrderId() {
        return evaluationOrderId;
    }

    public void setEvaluationOrderId(Integer evaluationOrderId) {
        this.evaluationOrderId = evaluationOrderId;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
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

    public EstateCategory getEstateCategory() {
        return estateCategory;
    }

    public void setEstateCategory(EstateCategory estateCategory) {
        this.estateCategory = estateCategory;
    }

    public List<EvaluationImage> getImages() {
        return images;
    }

    public void setImages(List<EvaluationImage> images) {
        this.images = images;
    }
}
