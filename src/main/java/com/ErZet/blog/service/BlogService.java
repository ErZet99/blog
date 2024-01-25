package com.ErZet.blog.service;

import com.ErZet.blog.dto.CommonPaginationRequest;
import com.ErZet.blog.dto.CreateBlogRequest;
import com.ErZet.blog.dto.UpdateBlogRequest;
import com.ErZet.blog.jpa.BlogRepository;
import com.ErZet.blog.model.Blog;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(CreateBlogRequest createBlogRequest) throws Exception{
        Blog blog = new Blog();
        BeanUtils.copyProperties(createBlogRequest, blog);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    public Blog updateBlog(UpdateBlogRequest updatedBlogRequest) throws Exception{
        Blog blog = blogRepository.findByBlogId(updatedBlogRequest.getBlogId());
        if(ObjectUtils.isEmpty(blog)) return null;      // handling if blog is not found
        BeanUtils.copyProperties(updatedBlogRequest, blog);
        blog.setUpdatedAt(LocalDateTime.now());
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
