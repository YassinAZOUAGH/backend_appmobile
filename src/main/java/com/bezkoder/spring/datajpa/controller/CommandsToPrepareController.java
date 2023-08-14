package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Command;
import com.bezkoder.spring.datajpa.model.CommandsToPrepare;
import com.bezkoder.spring.datajpa.repository.CommandRepository;
import com.bezkoder.spring.datajpa.repository.CommandsToPrepareRepository;
import com.bezkoder.spring.datajpa.service.CommandsToPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://192.168.0.246:4200")
//@CrossOrigin(origins = "http://smarteasyorders.com:4200")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommandsToPrepareController {
    @Autowired
    CommandsToPrepareRepository commandsToPrepareRepository;

    private final CommandsToPrepareService commandsToPrepareService;


    public CommandsToPrepareController(CommandsToPrepareService commandsToPrepareService) {
        this.commandsToPrepareService = commandsToPrepareService;
    }

    @GetMapping("/commandsToPrepare/getAll")
    public ResponseEntity<List<CommandsToPrepare>> getAllCommands() {
        try {
            List<CommandsToPrepare> commands = new ArrayList<CommandsToPrepare>();
            commandsToPrepareRepository.findAll().forEach(commands::add);
            return new ResponseEntity<>(commands, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/getall")
    public List<CommandsToPrepare> getAllData() {
        List<CommandsToPrepare> dataList = commandsToPrepareRepository.findAll();
        return dataList;
    }*/

    @PostMapping("/commandsToPrepare")
    public ResponseEntity<CommandsToPrepare> createCommand(@RequestBody CommandsToPrepare command) {
        try {
            CommandsToPrepare _command = commandsToPrepareRepository
                    .save(new CommandsToPrepare(
                            command.getContents(),
                            command.getDate(),
                            command.getTime(),
                            command.getTable(),
                            command.getPrice()
                    ));
            return new ResponseEntity<>(_command, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/commandsToPrepare/{id}")
    public ResponseEntity<CommandsToPrepare> updateCommand(@PathVariable("id") long id, @RequestBody CommandsToPrepare command) {
        Optional<CommandsToPrepare> commandData = commandsToPrepareRepository.findById(id);

        if (commandData.isPresent()) {
            CommandsToPrepare _command = commandData.get();
            _command.setContents(command.getContents());
            _command.setDate(command.getDate());
            _command.setTime(command.getTime());
            _command.setTable(command.getTable());
            _command.setPrice(command.getPrice());
            return new ResponseEntity<>(commandsToPrepareRepository.save(_command), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/commandsToPrepare/{id}")
    public ResponseEntity<CommandsToPrepare> getCommandById(@PathVariable("id") long id) {
        Optional<CommandsToPrepare> commandData = commandsToPrepareRepository.findById(id);

        if (commandData.isPresent()) {
            return new ResponseEntity<>(commandData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/commandsToPrepare/{id}")
    public ResponseEntity<HttpStatus> deleteCommand(@PathVariable("id") long id) {
        try {
            commandsToPrepareRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/commandsToPrepare")
    public ResponseEntity<HttpStatus> deleteAllCommands() {
        try {
            commandsToPrepareRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 }
