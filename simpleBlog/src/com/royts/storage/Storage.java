package com.royts.storage;

import com.royts.Post;

public interface Storage {

	Post savePost(Post post) throws StorageException, postNotFoundException;

}
