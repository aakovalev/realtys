package ru.camoroh13.realtys.domain;

import javax.persistence.*;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:09
 * Тип недвижимости. Жилая и т.д.
 */
@Entity
@Table(name = "estateTypes")
public class EstateType {

	@Id
	@Column(name = "estateTypeId")
	@GeneratedValue
	private Integer estateTypeId;

    @Column(name="name")
    private String name;

    public EstateType() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEstateTypeId() {
        return estateTypeId;
    }

    public void setEstateTypeId(Integer estateTypeId) {
        this.estateTypeId = estateTypeId;
    }
}
