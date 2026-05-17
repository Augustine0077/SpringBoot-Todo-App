package com.sum.Calculator.Service;

import com.sum.Calculator.Entity.Entity;
import com.sum.Calculator.Repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service

public class Service
{
    @Autowired
    private Repository repository;

    public Entity createUser(Entity entity) {
        return repository.save(entity);
    }

    public List<Entity> getAllUser() {
        return repository.findAll();
    }

    public Entity findId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void DeletUser(Long id) {
        repository.deleteById(id);
    }

    public Entity UpdateTodo(Long id, Entity newEntity) {
        Entity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity != null) {
            existingEntity.setTitle(newEntity.getTitle());
            existingEntity.setDescription(newEntity.getDescription());
            existingEntity.setIsCompleted(newEntity.getIsCompleted());
            return repository.save(existingEntity);
        }
        return null;
    }
    public Page<Entity>pageByNumber(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(pageable);

    }
}
