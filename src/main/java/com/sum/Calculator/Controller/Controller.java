package com.sum.Calculator.Controller;

import com.sum.Calculator.Entity.Entity;
import com.sum.Calculator.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/h")
    public String hello(){
        return "Hello world";
    }

    @PostMapping("/create")
    public ResponseEntity<Entity> createTodo(@RequestBody Entity entity) {
        Entity savedEntity = service.createUser(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Entity>> getAllUser() {
        List<Entity> entities = service.getAllUser();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entity> getTodoById(@PathVariable Long id) {
        Entity entity = service.findId(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeletUser(@PathVariable Long id) {
        Entity entity = service.findId(id);
        if (entity == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        service.DeletUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Entity> UpdateTodo(@PathVariable Long id, @RequestBody Entity entity) {
        Entity updatedEntity = service.UpdateTodo(id, entity);
        if (updatedEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }
    @GetMapping("/page")
    ResponseEntity<Page<Entity>> getTodoByPage(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(service.pageByNumber(page, size),HttpStatus.OK);
    }
}
