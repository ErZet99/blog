package com.ErZet.blog.controller;

import com.ErZet.blog.dto.CommonPaginationRequest;
import com.ErZet.blog.dto.CreateBlogRequest;
import com.ErZet.blog.dto.DBSResponseEntity;
import com.ErZet.blog.dto.UpdateBlogRequest;
import com.ErZet.blog.exception.RecordNotFoundException;
import com.ErZet.blog.model.Blog;
import com.ErZet.blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PutMapping("v1/blogs")
    public ResponseEntity<DBSResponseEntity> updateBlogCall(@Valid @RequestBody UpdateBlogRequest updateBlogRequest) {
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();

        try {
            Blog updatedBlog = blogService.updateBlog(updateBlogRequest);
            if(ObjectUtils.isEmpty(updatedBlog)) throw new RecordNotFoundException("Record not present in databse.");
            dbsResponseEntity.setMessage("Blog updated successfully.");
            dbsResponseEntity.setData(updatedBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (RecordNotFoundException exception) {
            throw exception;
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("v1/blogs")
    public ResponseEntity<DBSResponseEntity> createBlogCall(@Valid @RequestBody CreateBlogRequest createBlogRequest) {
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();

        log.info("BlogController:createdBlogCall request received with body : {}",
                createBlogRequest.toString());

        try {
            Blog createdBlog = blogService.createBlog(createBlogRequest);
            log.info("BlogService:updateBlog record save successfully with blogId : {}",
                    createdBlog.getBlogId());
            dbsResponseEntity.setMessage("Blog created successfully");
            dbsResponseEntity.setData(createdBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            log.debug("BlogController:updateBlogCall something wrong : {}", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("v1/blogs/{blogId}")
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

    @GetMapping("v1/blogs")
    public ResponseEntity<DBSResponseEntity> getBlogsCall(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy,
                                                          @RequestParam(defaultValue = "1") String userId) {
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();
        CommonPaginationRequest commonPaginationRequest = new CommonPaginationRequest();
        commonPaginationRequest.setPegeNo(pageNo);
        commonPaginationRequest.setPageSize(pageSize);
        commonPaginationRequest.setValue(userId);
        commonPaginationRequest.setSortBy(sortBy);
        try {
            List<Blog> blogs = blogService.getBlogs(commonPaginationRequest);
            dbsResponseEntity.setData(blogs);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("v1/blogs/{blogId}")
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
