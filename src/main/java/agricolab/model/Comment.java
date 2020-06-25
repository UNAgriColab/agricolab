package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    private String id;
    private String comment;
    private String orderReference;
    private double qualification;

    public Comment() {

    }

    public Comment(@JsonProperty("comment") String comment,
                   @JsonProperty("orderReference") String orderReference,
                   @JsonProperty("qualification") int qualification) {
        this.id = "";
        this.comment = comment;
        this.orderReference = orderReference;
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id='" + id + '\'' +
            ", comment='" + comment + '\'' +
            ", orderReference='" + orderReference + '\'' +
            ", qualification=" + qualification +
            '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public double getQualification() {
        return qualification;
    }

    public void setQualification(double qualification) {
        this.qualification = qualification;
    }
}
