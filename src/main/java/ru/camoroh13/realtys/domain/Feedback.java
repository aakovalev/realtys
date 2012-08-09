package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Konstantin
 * Date: 21.08.11
 * Time: 13:36
 */
@Entity
@Table(name = "feedbacks")
public class Feedback implements Serializable{

    private static final long serialVersionUID = -4180115039396717338L;

	@Id
	@Column(name = "feedbackId")
	@GeneratedValue
	private Integer feedbackId;

    @Column(name= "name",
            columnDefinition = "",
            nullable = false
    )
    private String name;

    @Column(name= "email",
            columnDefinition = "",
            nullable = false
    )
    private String email;

    @Column(name= "phone",
            columnDefinition = "",
            nullable = false
    )
    private String phone;

    @Lob
    @Column(name= "text",
            columnDefinition = "",
            nullable = false
    )
    private String text;

    @Column(name = "dateTime")
    private Date dateTime = new Date();

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
