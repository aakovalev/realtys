package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Konstantin
 * Date: 05.09.11
 */
@Entity
@Table(name = "images")
public class Image implements Serializable{

    private static final long serialVersionUID = 2497526983427114985L;

	@Id
	@Column(name = "imageId")
	@GeneratedValue
	private Integer imageId;

    @Column(name="image")
    private String image;

    @ManyToOne(targetEntity = Estate.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    Estate estate;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
