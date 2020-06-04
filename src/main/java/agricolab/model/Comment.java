package agricolab.model;

public class Comment {

    private String id;
    private String commentario;
    private String OfferReference;

    public Comment() {

    }

    public Comment(String id, String commentario, String OfferReference) {
        this.id = id;
        this.commentario = commentario;
        this.OfferReference = OfferReference;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id='" + id + '\'' +
                ", commentario='" + commentario + '\'' +
                ", sellerEmail='" + OfferReference + '\'' +
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

    public String getOfferReference() {
        return OfferReference;
    }

    public void setOfferReference(String offerReference) {
        this.OfferReference = offerReference;
    }
}
