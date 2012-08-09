package ru.camoroh13.realtys.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Konstantin
 * Date: 13.10.11
 */
@Entity
@Table(name = "evaluationImages")
public class EvaluationImage implements Serializable {

    private static final long serialVersionUID = 7391277287080751924L;

	@Id
	@Column(name = "imageId")
	@GeneratedValue
	private Integer imageId;

    @Column(name="image")
    private String image;

    @ManyToOne(targetEntity = EvaluationOrder.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    EvaluationOrder evaluationOrder;

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

    public EvaluationOrder getEvaluationOrder() {
        return evaluationOrder;
    }

    public void setEvaluationOrder(EvaluationOrder evaluationOrder) {
        this.evaluationOrder = evaluationOrder;
    }
}
