package com.app.mybatis.mapper;

import com.app.mybatis.domain.dto.PostCountDTO;
import com.app.mybatis.domain.dto.PostDTO;
import com.app.mybatis.domain.dto.PostResponseDTO;
import com.app.mybatis.domain.vo.MemberVO;
import com.app.mybatis.domain.vo.PostLikeVO;
import com.app.mybatis.domain.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Slf4j
public class PostMapperTests {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Test
    public void insertTest(){
        PostVO postVO = new PostVO();
        postVO.setPostTitle("오늘 점심 뭐 먹지?");
        postVO.setPostContent("아무거나");
        postVO.setMemberId(1L);
        postMapper.insert(postVO);
    }

    @Test
    public void selectAllTest(){
        postMapper.selectAll(null).stream().map(PostDTO::toString).forEach(log::info);
    }

    @Test
    public void selectTest(){
        Long postId = 2L;
        PostVO postVO = new PostVO();
        postVO.setId(postId);
        postVO.setPostReadCount(1L);
        postMapper.updatePostReadCount(2L);
        postMapper.select(postId).map(PostDTO::toString).ifPresent(log::info);
    }

    @Test
    public void updateTest(){
        PostVO postVO = new PostVO();
        postVO.setId(2L);
        postVO.setPostTitle("오늘 점심은 배터지게 드세요!!");
        postVO.setPostContent("아무거나");
        postVO.setMemberId(1L);

        postMapper.update(postVO);
        postMapper.select(2L).map(PostDTO::toString).ifPresent(log::info);
    }

    @Test
    public void deleteTest(){
        postMapper.delete(2L);
    }

    @Test
    public void postUpdateReadCountTest(){
        PostVO postVO = new PostVO();
        postVO.setId(2L);
        postVO.setPostReadCount(1L);
        postMapper.updatePostReadCount(2L);
    }

    @Test
    public void insertTest2(){
//        한사람이 50개의 글을 작성하게
        for(int i = 0; i < 50; i++){
            PostVO postVO = new PostVO();
            postVO.setPostTitle("테스트 게시글" + (i + 1));
            postVO.setPostContent("테스트 내용" + (i + 1));
            postVO.setMemberId(1L);
            postMapper.insert(postVO);
        }
    }

    @Test
    public void updateReadCountTest(){
        Random random = new Random();
        random.nextInt(50);
        for(int i = 0; i < 5000; i++){
            Long id = Long.valueOf(random.nextInt(51));
            postMapper.updatePostReadCount(id);
        }
    }

    @Test
    public void selectAllWithOrderTest(){
        HashMap<String, Object> orders = new HashMap<>();
        orders.put("order", "popular");
        orders.put("cursor", 1);
        orders.put("limit", 10);

        postMapper
                .selectAllWithOrder(orders)
                .stream()
                .map(PostDTO::toString)
                .forEach(log::info);
    }

    @Test
    public void selectTotalPostCountAndTotalPageCountTest(){
        int limit = 10;
        PostCountDTO postCountDTO = postMapper.selectTotalPostCountAndTotalPageCount(limit);
        log.info("totalCountDTO: {}", postCountDTO);
    }

    //    게시판 목록 조회
    @Test
    public void selectPostsTest(){
        int limit = 10;

        HashMap<String, Object> orders = new HashMap<>();
        orders.put("order", "popular");
        orders.put("cursor", 1);
        orders.put("limit", 10);

        List<PostDTO> posts = postMapper.selectAllWithOrder(orders);
        PostCountDTO postCountDTO = postMapper.selectTotalPostCountAndTotalPageCount(limit);

        /* 최종 응답 */
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setTotalPostCount(postCountDTO.getTotalPostCount());
        postResponseDTO.setTotalPageCount(postCountDTO.getTotalPageCount());
        postResponseDTO.setPosts(posts);

        log.info("postResponseDTO: {}", postResponseDTO);

    }

//    @Test
//    public void selectTest2(){
//
//        String keyword = "게시글80";
//
//        List<PostDTO> searchResults = postMapper.selectAll();
//        List<PostDTO> allResult = postMapper.selectAll(null);
//
//        if(!searchResults.isEmpty()){
//        log.info("searchResults: {}", searchResults);
//        } else{
//        log.info("allResult: {}", allResult);
//        }
//    }

    @Test
    public void postLikeTest(){
        PostLikeVO postLikeVO = new PostLikeVO();

        //  1~50
        for (int i = 0; i < 50; i++){

        Random random = new Random();
        int randomNumber = random.nextInt(1, 51);
        postLikeVO.setPostId(Long.valueOf(randomNumber));

        randomNumber = random.nextInt(1,4);
        postLikeVO.setMemberId(Long.valueOf(randomNumber));
        postLikeMapper.insert(postLikeVO);
        }
    }


    // 내가 좋아요를 누른 게시글


}
