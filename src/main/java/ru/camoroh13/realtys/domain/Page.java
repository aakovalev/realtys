package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Konstantin
 * Date: 20.07.11
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "pages")
public class Page implements Serializable{

	@Id
	@Column(name = "pageId")
	@GeneratedValue
	private Integer pageId;

    @Column(name="url")
    private String url;

    @Column(name="metaTitle")
    private String metaTitle;

    @Lob
    @Column(name="metaDescription")
    private String metaDescription;

    @Lob
    @Column(name="metaKeywords")
    private String metaKeywords;

    @Lob
    @Column(name="title")
    private String title;

    @Lob
    @Column(name="text")
    private String text;

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
