package io.jc.item.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jc.item.repo.Item;
import io.jc.item.repo.ItemRepository;

/*
 * Service Class for Item
 */
@Service
public class ItemService {

	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	void initialize() {
		Item item1 = new Item("Ponds", "Fairness Cream", "75.00");
		Item item2 = new Item("Johnsons", "Baby Soap", "40.00");
		itemRepository.save(item1);
		itemRepository.save(item2);
	}

	/**
	 * Gets List of Items from Item Table
	 * 
	 * @return Item List
	 * @throws Exception 
	 */
	public List<Item> getItems() throws Exception {
		List<Item> cust = new ArrayList<Item>();
		try {
			cust = itemRepository.findAll();
		} catch (Exception e) {
			log.error("Item Repository Returned no records");
			throw new Exception(e);
		}
		return cust;
	}

	/**
	 * Gets Item of particular id from Item Table
	 * 
	 * @return Item
	 */
	public Optional<Item> getItem(Long id) {
		Optional<Item> item;
		item=	itemRepository.findById(id);
		if(item.isPresent()) {
			log.info("Item is present , id {}",id);
		}
		return item;
	}

	/**
	 * Creates a new Item
	 */
	public void createItem(Item item) {
		itemRepository.save(item);
		log.info("Item Created");

	}

}
