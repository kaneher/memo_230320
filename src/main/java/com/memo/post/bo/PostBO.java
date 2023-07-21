package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostMapper;
import com.memo.post.domain.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostMapper postMapper; // Mybatis
	
	@Autowired
	private FileManagerService fileManager;

	// input : userId(글쓴이)
	// output : List<Post>
	public List<Post> getPostListByUserId(int userId) {
		return postMapper.selectPostListByUserId(userId);
	}
	
	// input : request parameter
	// output : 성공된 행의 개수
	public int addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		
		String imagePath = null;
		
		// 이미지가 있으면 업로드 후 imagePath 받아오기
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		return postMapper.insertPost(userId, subject, content, imagePath);
	}
	
	// input : postId, userId
	// output : Post
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdAndUserId(postId, userId);
	}
}
