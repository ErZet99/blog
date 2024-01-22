package com.ErZet.blog.service;

import com.ErZet.blog.dto.CommonPaginationRequest;
import com.ErZet.blog.jpa.BlogRepository;
import com.ErZet.blog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(Blog blog) throws Exception{
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Blog blog) throws Exception{
        return blogRepository.save(blog);
    }

    public Blog deleteBlog(String blogId) throws Exception{
        return blogRepository.deleteByBlogId(blogId);
    }

    public Blog getBlog(String blogId) throws Exception{
        return blogRepository.findByBlogId(blogId);
    }
    public List<Blog> getBlogs(CommonPaginationRequest commonPaginationRequest) throws Exception{
        Pageable pageable = (Pageable) PageRequest.of(commonPaginationRequest.getPegeNo(),
                commonPaginationRequest.getPageSize(),
                Sort.by(commonPaginationRequest.getSortBy()).descending());
        return blogRepository.findByUserId(commonPaginationRequest.getValue(), pageable);
    }
}
