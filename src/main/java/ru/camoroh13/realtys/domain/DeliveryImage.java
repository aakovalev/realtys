package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Konstantin
 * Date: 12.10.11
 */
@Entity
@Table(name = "deliveryImages")
public class DeliveryImage implements Serializable {

    private static final long serialVersionUID = 7391277287080751924L;

	@Id
	@Column(name = "imageId")
	@GeneratedValue
	private Integer imageId;

    @Column(name="image")
    private String image;

    @ManyToOne(targetEntity = DeliveryOrder.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    DeliveryOrder deliveryOrder;

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

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

}
