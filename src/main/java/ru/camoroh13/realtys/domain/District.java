package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "districts")
public class District implements Serializable {

	@Id
	@Column(name = "districtId")
	@GeneratedValue
	private Integer districtId;

    @Column(name="name")
    private String name;

    public District() {

    }

    public District(String name) {
        this.name = name;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
