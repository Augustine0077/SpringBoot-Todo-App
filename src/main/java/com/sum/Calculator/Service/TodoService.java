package com.sum.Calculator.service;

import com.sum.Calculator.models.Todo;
import com.sum.Calculator.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo findId(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodo(Long id, Todo newTodo) {
        Todo existingTodo = todoRepository.findById(id).orElse(null);
        if (existingTodo != null) {
            existingTodo.setTitle(newTodo.getTitle());
            existingTodo.setDescription(newTodo.getDescription());
            existingTodo.setIsCompleted(newTodo.getIsCompleted());
            return todoRepository.save(existingTodo);
        }
        return null;
    }

    public Page<Todo> pageByNumber(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }
}
