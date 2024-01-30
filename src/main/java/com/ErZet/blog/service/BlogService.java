package com.ErZet.blog.service;

import com.ErZet.blog.dto.CommonPaginationRequest;
import com.ErZet.blog.dto.CreateBlogRequest;
import com.ErZet.blog.dto.UpdateBlogRequest;
import com.ErZet.blog.jpa.BlogRepository;
import com.ErZet.blog.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames="blogs")
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

    @CachePut(key="#updatedBlogRequest.blogId") // update cache also
    public Blog updateBlog(UpdateBlogRequest updatedBlogRequest) throws Exception{
        Blog blog = blogRepository.findByBlogId(updatedBlogRequest.getBlogId());
        if(ObjectUtils.isEmpty(blog)) return null;      // handling if blog is not found
        BeanUtils.copyProperties(updatedBlogRequest, blog);
        blog.setUpdatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    @CacheEvict(key="#blogId")   // delete cache also
    public Blog deleteBlog(String blogId) throws Exception{
        return blogRepository.deleteByBlogId(blogId);
    }

    @Cacheable(key="#blogId")    // before ask database for blog with blogId, ask cache (hashTable) for that
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
