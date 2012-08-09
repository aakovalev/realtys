package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Konstantin
 * Date: 13.10.11
 */
@Entity
@Table(name="rentImage")
public class RentImage implements Serializable {

    private static final long serialVersionUID = 8449286631268138429L;

    @Id
	@Column(name = "imageId")
	@GeneratedValue
	private Integer imageId;

    @Column(name="image")
    private String image;

    @ManyToOne(targetEntity = RentOrder.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    RentOrder rentOrder;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public RentOrder getRentOrder() {
        return rentOrder;
    }

    public void setRentOrder(RentOrder rentOrder) {
        this.rentOrder = rentOrder;
    }
}
