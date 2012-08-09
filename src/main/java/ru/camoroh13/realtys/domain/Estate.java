package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "estate")
public class Estate implements Serializable {
    
    public static final String NO_IMAGE = "noimage.jpg";

    private static final String DEFAULT_TEXT = " ";
    private static final String DEFAULT_BENEFITS = DEFAULT_TEXT;
    private static final String DEFAULT_ADDRESS = DEFAULT_TEXT;
    private static final Integer DEFAULT_PRICE = Integer.valueOf(0);

	@Id
	@Column(name = "estateId")
	@GeneratedValue
	private Integer estateId;

    @Column(name="code",
            unique = true)
    private String code;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    @Column(name="address",
            nullable = false,
            columnDefinition = "")
    private String address;

    @Column(name="square",
            nullable = false,
            columnDefinition = "0")
    private Integer square;

    @Column(name="floor",
            nullable = false,
            columnDefinition = "0")
    private Integer floor;

    @Column(name="maxFloor",
            nullable = false,
            columnDefinition = "0")
    private Integer maxFloor;

    @Column(name="price",
            nullable = false,
            columnDefinition = "0")
    private Integer price;

    @Column(name="special")
    private Boolean special;

    @Column(name="rooms")
    private Integer rooms;

    @Lob
    @Column(nullable = false,
            columnDefinition = "")
    private String text;

    @Lob
    @Column(nullable = false,
            columnDefinition = " ")
    private String benefits;

    @Column(name="image")
    private String image = NO_IMAGE;

    @Column(name="with_photo")
    private Boolean withPhoto;

    @OneToMany (mappedBy = "estate",
            fetch = FetchType.EAGER)
    private List<Image> images;

    @ManyToOne(targetEntity = District.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    District district;

    @ManyToOne(targetEntity = EstateType.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    EstateType estateType;

    @ManyToOne(targetEntity = EstateCategory.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    EstateCategory estateCategory;

    public Estate() {
        text = "";
        rooms = 0;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = getNotNullValue(price, DEFAULT_PRICE);
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = getNotNullOrEmptyString(code, generateCode());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = getNotNullOrEmptyString(address, DEFAULT_ADDRESS);
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public EstateType getEstateType() {
        return estateType;
    }

    public void setEstateType(EstateType estateType) {
        this.estateType = estateType;
    }

    public EstateCategory getEstateCategory() {
        return estateCategory;
    }

    public void setEstateCategory(EstateCategory estateCategory) {
        this.estateCategory = estateCategory;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = getNotNullOrEmptyString(text, DEFAULT_TEXT);
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = getNotNullOrEmptyString(benefits, DEFAULT_BENEFITS);
    }
    
    public boolean hasImage() {
        return ! NO_IMAGE.equals(image);
    }

    public Boolean getWithPhoto() {
        return withPhoto;
    }

    public void setWithPhoto(Boolean withPhoto) {
        this.withPhoto = withPhoto;
    }

    private <T> T getNotNullValue(T value, T defaultValue) {
        if (defaultValue == null) {
            throw new IllegalArgumentException("defaultValue must not be null!");
        }
        return value != null ? value : defaultValue;
    }

    private String getNotNullOrEmptyString(String stringValue, String defaultStringValue) {
        if (defaultStringValue == null) {
            throw new IllegalArgumentException("defaultStringValue must not be null!");
        }
        return stringValue != null && !stringValue.isEmpty() ? stringValue : defaultStringValue;
    }

    private String generateCode() {
        return new Date().getTime() + "";
    }
}
