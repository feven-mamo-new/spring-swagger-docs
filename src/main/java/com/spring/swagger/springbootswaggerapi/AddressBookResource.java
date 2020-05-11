package com.spring.swagger.springbootswaggerapi;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class AddressBookResource {

    private final static Logger logger= LoggerFactory.getLogger(AddressBookResource.class);

    ConcurrentHashMap<String, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    @ApiOperation(value = "Find contacts by id",
                  notes = "Provide an id to look up specific contact from the address book",
                  response = Contact.class )
    public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve", required = true)
                              @PathVariable String id){
        return contacts.get(id);
    }

    @GetMapping("/")
    public List<Contact> getAllContact(){
        logger.trace("Get all contacts method called...");
        return new ArrayList<Contact>(contacts.values());
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact){
        contacts.put(contact.getId(), contact);
        return contact;
    }
}
