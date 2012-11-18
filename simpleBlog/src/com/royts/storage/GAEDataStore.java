package com.royts.storage;

import java.util.ConcurrentModificationException;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.royts.Post;

public class GAEDataStore implements Storage {

	private DatastoreService appEngineDS;

	public GAEDataStore(DatastoreService dataStore) {
		this.appEngineDS = dataStore;
	}

	@Override
	public Post savePost(Post postToSave) throws StorageException, postNotFoundException {

		if (postToSave == null) {
			throw new IllegalArgumentException("Received null post");
		}

		Entity entity = getEntityByPostId(postToSave.getId());

		setEntityProperties(postToSave, entity);

		savePostToDB(entity);

		return new Post(new Long(entity.getKey().getId()), postToSave);
	}

	private Entity getEntityByPostId(long postId) throws StorageException,
			postNotFoundException {
		Entity entity = null;

		if (postId < 0) {
			entity = new Entity(GAEConsts.POST_ENTITY_NAME);
		} else {
			try {

				return this.appEngineDS.get(KeyFactory.createKey(
						GAEConsts.POST_ENTITY_NAME, postId));
			} catch (ConcurrentModificationException ex1) {
				throw new StorageException(ex1);
			} catch (DatastoreFailureException ex2) {
				throw new StorageException(ex2);
			} catch (EntityNotFoundException e) {
				throw new postNotFoundException(e);
			}

		}

		return entity;
	}

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

}
