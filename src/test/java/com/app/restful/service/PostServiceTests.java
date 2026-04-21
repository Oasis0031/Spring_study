package com.app.restful.service;

import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
<<<<<<< HEAD
    public void getPostList(){
=======
    public void getPostListTest(){
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        log.info("postService : {}", postService.getPostList("oldest"));
    }

    @Test
<<<<<<< HEAD
    public void getPostDetail(){
=======
    public void getPostDetailTest(){
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        log.info("postService : {}", postService.getPostDetail(1L));
    }

    @Test
<<<<<<< HEAD
    public void createPostTest() {
        PostCreateRequestDTO postCreateRequestDTO = new PostCreateRequestDTO();
        postCreateRequestDTO.setPostTitle("세로운 게시글1");
        postCreateRequestDTO.setPostContent(("새로운 게시글 내용1"));
=======
    public void createPostTest(){
        PostCreateRequestDTO postCreateRequestDTO = new PostCreateRequestDTO();
        postCreateRequestDTO.setPostTitle("새로운 게시글1");
        postCreateRequestDTO.setPostContent("새로운 게시글 내용1");
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        postService.createPost(postCreateRequestDTO, 1L);
        log.info("postService : {}", postService.getPostList("desc"));
    }

<<<<<<< HEAD

=======
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
    @Test
    public void modifyPostTest(){
        PostUpdateRequestDTO postUpdateRequestDTO = new PostUpdateRequestDTO();
        postUpdateRequestDTO.setPostTitle("새로운 수정1");
<<<<<<< HEAD
        postUpdateRequestDTO.setPostContent(("새로운 수정 내용1"));
        postService.modifyPost(postUpdateRequestDTO, 1L);
=======
        postUpdateRequestDTO.setPostContent("새로운 수정 내용1");
        postService.modifyPost(postUpdateRequestDTO, 81L);
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        log.info("postService : {}", postService.getPostList("desc"));
    }

    @Test
<<<<<<< HEAD
    public void removePostTest(){
        postService.remove(63L);
    }



























=======
    public void removeTest(){
        postService.remove(81L);
        log.info("postService : {}", postService.getPostList("desc"));
    }

>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
}
