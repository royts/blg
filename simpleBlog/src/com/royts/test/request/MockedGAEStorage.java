package com.royts.test.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.royts.Post;
import com.royts.PostDetails;
import com.royts.storage.Storage;
import com.royts.storage.StorageException;

public class MockedGAEStorage implements Storage {

	List<Post> posts = new ArrayList<Post>();
	private List<Post> savedPosts = new ArrayList<Post>();

	public List<Post> getSavedPosts() {
		return savedPosts;
	}

	@Override
	public Post savePost(Post post) throws StorageException {
		Post savedPost = post;

		if (savedPost.getId() < 0) {
			// Random ran
			savedPost = new Post(new Long(new Random().nextLong()), post);
		}

		this.savedPosts.add(savedPost);

		return savedPost;
	}

	@Override
	public List<PostDetails> getAllPostsDetails() {
		List<PostDetails> postsDetails = new ArrayList<PostDetails>();
		for (Post post : posts) {
			postsDetails.add(new PostDetails(post.getId(), post.getTitle()));
		}
		return postsDetails;
	}

	public void addPost(Post post) {
		this.posts.add(post);

	}

	@Override
	public Post getPostById(Long postId) {
		Post found = null;

		for (Post current : posts) {
			if (current.getId().equals(postId)) {
				found = current;
				break;
			}
		}

		return found;
	}

}
