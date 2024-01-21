package com.ErZet.blog.service;

import com.ErZet.blog.jpa.CommentRepository;
import com.ErZet.blog.model.Blog;
import com.ErZet.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) throws Exception{
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) throws Exception{
        return commentRepository.save(comment);
    }

    public Comment deleteComment(String commentId) throws Exception{
        return commentRepository.deleteByCommentId(commentId);
    }

    public List<Comment> getComments(String blogId, Pageable pageable) throws Exception{
        return commentRepository.findByBlogId(blogId, pageable);
    }
}
