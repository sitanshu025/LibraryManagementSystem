package com.bca.librarymanagementsystem.service;

import java.util.List;

import com.bca.librarymanagementsystem.entity.Publisher;

public interface PublisherService {

	List<Publisher> findAllPublishers();

	Publisher findPublisherById(Long id);

	void createPublisher(Publisher publisher);

	void updatePublisher(Publisher publisher);

	void deletePublisher(Long id);

}
