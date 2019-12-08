package io.jc.item.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jc.item.errorHandler.ItemException;
import io.jc.item.errorHandler.ItemNotFoundException;
import io.jc.item.repo.Item;
import io.jc.item.service.ItemService;

@RestController
@RequestMapping("item-service")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "item", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String create(@RequestBody Item item) {

		itemService.createItem(item);

		return "Item Created";

	}

	@GetMapping(path = "/items", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Item> retrieveAllItems() throws ItemException, ItemNotFoundException {
		List<Item> itm = new ArrayList<Item>();
		try {
			itm = itemService.getItems();

			if (itm.isEmpty()) {
				throw new ItemNotFoundException();
			}

		} catch (ItemNotFoundException e) {
			throw new ItemNotFoundException("No Items are available in the table");
		}

		catch (Exception e) {
			throw new ItemException("Exception while retrieving Item Details ", e);
		}
		return itm;
	}

	@GetMapping(path = "/item/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Item retrieveStudent(@PathVariable Long id) throws ItemNotFoundException, ItemException {
		Optional<Item> item;
		try {
			item = itemService.getItem(id);
			if (!item.isPresent()) {
				throw new ItemNotFoundException();
			}
		} catch (ItemNotFoundException e) {
			throw new ItemNotFoundException("Item of id:" + id + " is not available in the table");
		} catch (Exception e) {
			throw new ItemException("Exception while retrieving Item Detail ", e);
		}
		return item.get();
	}
}
