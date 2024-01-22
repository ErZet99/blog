package com.ErZet.blog.controller;

import com.ErZet.blog.dto.CreateBlogRequest;
import com.ErZet.blog.dto.DBSResponseEntity;
import com.ErZet.blog.dto.UpdateBlogRequest;
import com.ErZet.blog.model.Blog;
import com.ErZet.blog.service.BlogService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PutMapping("v1/blogs")
    public ResponseEntity<DBSResponseEntity> updateBlogCall(@RequestBody UpdateBlogRequest updateBlogRequest) {
        Blog blog = new Blog();
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();

        try {
            BeanUtils.copyProperties(updateBlogRequest, blog);
            Blog updatedBlog = blogService.updateBlog(blog);
            dbsResponseEntity.setMessage("Blog updated successfully.");
            dbsResponseEntity.setData(updatedBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("v1/blogs")
    public ResponseEntity<DBSResponseEntity> createBlogCall(@Valid @RequestBody CreateBlogRequest createBlogRequest) {
        Blog blog = new Blog();
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();

        try {
            BeanUtils.copyProperties(createBlogRequest, blog);
            Blog createdBlog = blogService.createBlog(blog);
            dbsResponseEntity.setMessage("Blog created successfully");
            dbsResponseEntity.setData(createdBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("v1/blogs/{blogId")
    public ResponseEntity<DBSResponseEntity> getBlogCall(@PathVariable String blogId) {
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();

        try {
            Blog getBlog = blogService.getBlog(blogId);
            dbsResponseEntity.setData(getBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<DBSResponseEntity> deleteBlogCall(@PathVariable String blogId) {
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();

        try {
            blogService.deleteBlog(blogId);
            dbsResponseEntity.setMessage("Blog deleted successfully");
            return ResponseEntity.ok(dbsResponseEntity);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
