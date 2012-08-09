package ru.camoroh13.realtys.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "publications")
public class Publication implements Serializable {

	@Id
	@Column(name = "publicationId")
	@GeneratedValue
	private Integer id;

/*    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;*/

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "publications2categories",
    joinColumns = @JoinColumn(name = "publicationId"),
    inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> categoriesList;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

	@Column(name = "url")
	private String url;

	@Column(name = "title")
	private String title;

    @Type(type = "text")
	@Column(name = "anons")
	private String anons;

    @Lob
	@Column(name = "text")
	private String text;

    public Publication() {
        url = "";
        title = "";
        anons = "";
        text = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public void addCategory(Category category) {
        categoriesList.add(category);
    }

    public void addCategory(List<Category> category) {
        categoriesList.addAll(category);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}