package ru.camoroh13.realtys.domain;

import ru.camoroh13.realtys.domain.Publication;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

	@Id
	@Column(name = "categoryId")
	@GeneratedValue
	private Integer id;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Collection<Category> subcategory;    

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "publications2categories",
    joinColumns = @JoinColumn(name = "categoryId"),
    inverseJoinColumns = @JoinColumn(name = "publicationId"))
    private Set<Publication> publicationsList;

    @Column(name="url")
    private String url;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Category> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Collection<Category> subcategory) {
        this.subcategory = subcategory;
    }

    public void setSubcategory(Category subcategory) {
        this.subcategory.add(subcategory);
    }

    public Set<Publication> getPublicationsList() {
        return publicationsList;
    }

    public void setPublicationsList(Set<Publication> publicationsList) {
        this.publicationsList = publicationsList;
    }
}
