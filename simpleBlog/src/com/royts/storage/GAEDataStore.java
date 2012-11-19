package com.royts.storage;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.royts.Post;
import com.royts.PostDetails;

public class GAEDataStore implements Storage {

	private DatastoreService appEngineDS;

	public GAEDataStore(DatastoreService dataStore) {
		this.appEngineDS = dataStore;
	}

	@Override
	public Post savePost(Post postToSave) throws StorageException {

		if (postToSave == null) {
			throw new IllegalArgumentException("Received null post");
		}

		Entity entity = new Entity(GAEConsts.POST_ENTITY_NAME);
		// getEntityByPostId(postToSave.getId());

		setEntityProperties(postToSave, entity);

		savePostToDB(entity);

		return new Post(new Long(entity.getKey().getId()), postToSave);
	}

	// private Entity getEntityByPostId(long postId) throws StorageException {
	// Entity entity = null;

	// if (postId < 0) {
	// entity = new Entity(GAEConsts.POST_ENTITY_NAME);
	// } else {
	// try {
	//
	// return
	// this.appEngineDS.get(KeyFactory.createKey(GAEConsts.POST_ENTITY_NAME,
	// postId));
	//
	// } catch (ConcurrentModificationException ex1) {
	// throw new StorageException(ex1);
	// } catch (DatastoreFailureException ex2) {
	// throw new StorageException(ex2);
	// } catch (EntityNotFoundException e) {
	// throw new postNotFoundException(e);
	// }
	// }

	// return entity;
	// }

	private void savePostToDB(Entity entity) throws StorageException {
		try {
			this.appEngineDS.put(entity);
		} catch (ConcurrentModificationException ex1) {
			throw new StorageException(ex1);
		} catch (DatastoreFailureException ex2) {
			throw new StorageException(ex2);
		}

	}

	private void setEntityProperties(Post post, Entity entity) {
		entity.setProperty(GAEConsts.POST_ID_PROP_NAME, post.getId());
		entity.setProperty(GAEConsts.POST_TITLE_PROP_NAME, post.getTitle());
		entity.setProperty(GAEConsts.POST_CONTENT_PROP_NAME, post.getContent());
		entity.setProperty(GAEConsts.POST_AUTHOR_MAIL_PROP_NAME,
				post.getAuthorsMail());
	}

	@Override
	public List<PostDetails> getAllPostsDetails() {
		List<PostDetails> postsDetails = new ArrayList<PostDetails>();

		Query query = new Query(GAEConsts.POST_ENTITY_NAME);

		PreparedQuery preparedQuery = this.appEngineDS.prepare(query);
		List<Entity> postsEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());

		for (Entity entity : postsEntities) {
			postsDetails.add(createPostDetailsFromEntity(entity));
		}

		return postsDetails;
	}

	@Override
	public Post getPostById(Long postId) throws StorageException {
		if (postId == null) {
			throw new IllegalArgumentException("Received post ID null");
		}

		Post postToreturn = null;
		Entity postEntity = null;

		try {
			postEntity = getPostEntity(postId);
			postToreturn = createPostFromEntity(postEntity);
		} catch (EntityNotFoundException e) {
		}

		return postToreturn;
	}

	private PostDetails createPostDetailsFromEntity(Entity postEntity) {
		return new PostDetails(
				new Long(postEntity.getKey().getId()),
				(String) postEntity.getProperty(GAEConsts.POST_TITLE_PROP_NAME));
	}

	private Post createPostFromEntity(Entity postEntity) {
		return new Post(
				new Long(postEntity.getKey().getId()),
				(String) postEntity.getProperty(GAEConsts.POST_TITLE_PROP_NAME),
				(String) postEntity.getProperty(GAEConsts.POST_CONTENT_PROP_NAME),
				(String) postEntity.getProperty(GAEConsts.POST_AUTHOR_MAIL_PROP_NAME));
	}

	private Entity getPostEntity(Long entityKey) throws EntityNotFoundException, StorageException {
		try {

			return appEngineDS.get(KeyFactory.createKey(GAEConsts.POST_ENTITY_NAME, entityKey));

		} catch (ConcurrentModificationException ex1) {
			throw new StorageException(ex1);
		} catch (DatastoreFailureException ex2) {
			throw new StorageException(ex2);
		}
	}

}
