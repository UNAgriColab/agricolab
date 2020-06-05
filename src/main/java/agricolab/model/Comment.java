package agricolab.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    private String id;
    private String commentario;
    private String orderReference;
    private int calificacion;

    public Comment() {

    }

    public Comment(@JsonProperty("id")String id,
                   @JsonProperty("commentario")String commentario,
                   @JsonProperty("offerReference")String offerReference ,
                   @JsonProperty("calificacion")int calificacion) {
        this.id = id;
        this.commentario = commentario;
        this.orderReference = offerReference;
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentario='" + commentario + '\'' +
                ", orderReference='" + orderReference + '\'' +
                ", calificacion='" + calificacion + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentario() {
        return commentario;
    }

    public void setCommentario(String commentario) {
        this.commentario = commentario;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
