package com.commafeed.backend.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.commafeed.backend.model.FeedEntry;
import com.commafeed.backend.model.FeedEntryTag;
import com.commafeed.backend.model.QFeedEntryTag;
import com.commafeed.backend.model.User;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class FeedEntryTagDAO extends GenericDAO<FeedEntryTag> {

	private final QFeedEntryTag tag = QFeedEntryTag.feedEntryTag;

	@Inject
	public FeedEntryTagDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<String> findByUser(User user) {
		return query().selectDistinct(tag.name).from(tag).where(tag.user.eq(user)).fetch();
	}

	public List<FeedEntryTag> findByEntry(User user, FeedEntry entry) {
		return query().selectFrom(tag).where(tag.user.eq(user), tag.entry.eq(entry)).fetch();
	}
}
