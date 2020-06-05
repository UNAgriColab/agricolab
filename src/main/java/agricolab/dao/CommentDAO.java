package agricolab.dao;

import agricolab.model.Comment;
import agricolab.model.ID;

import java.util.ArrayList;

public interface CommentDAO {

    ID setOfferId();

    boolean createComment(Comment comment);

    Comment getComment(String id);

    ArrayList<Comment> getAllCommentsByOffer( String offerId);
}
