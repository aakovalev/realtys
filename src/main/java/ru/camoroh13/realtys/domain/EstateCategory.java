package ru.camoroh13.realtys.domain;

import javax.persistence.*;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="estateCategories")
public class EstateCategory {

	@Id
	@Column(name = "estateCategoryId")
	@GeneratedValue
	private Integer estateCategoryId;

    @Column(name="name")
    private String name;

    public EstateCategory() {}

    public Integer getEstateCategoryId() {
        return estateCategoryId;
    }

    public void setEstateCategoryId(Integer estateCategoryId) {
        this.estateCategoryId = estateCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
