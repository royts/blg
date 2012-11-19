package com.royts.storage;

import java.util.List;

import com.royts.Post;
import com.royts.PostDetails;

public interface Storage {

	Post savePost(Post post) throws StorageException;
	List<PostDetails> getAllPostsDetails ();
	Post getPostById (Long id) throws StorageException ;

}
