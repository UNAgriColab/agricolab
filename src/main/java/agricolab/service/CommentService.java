package agricolab.service;


import agricolab.dao.CommentDAO;
import agricolab.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {

    private  CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public boolean addComment(Comment comment){
        return commentDAO.createComment(comment);
    }

    public ArrayList<Comment> getAllCommentsByOffer (String OfferRefference){
        return commentDAO.getAllCommentsBySeller(OfferRefference);
    }
}
