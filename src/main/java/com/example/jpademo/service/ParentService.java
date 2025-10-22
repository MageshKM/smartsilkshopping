package com.example.jpademo.service;

import com.example.jpademo.model.Child;
import com.example.jpademo.model.Parent;
import com.example.jpademo.repository.ChildRepository;
import com.example.jpademo.repository.ParentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ParentService {
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    public ParentService(ParentRepository parentRepository, ChildRepository childRepository) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    @Transactional
    public Parent saveParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Transactional(readOnly = true)
    public Optional<Parent> getParent(Long id) {
        return parentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Child> getChild(Long id) {
        return childRepository.findById(id);
    }

    @Transactional
    public void deleteChild(Long childId) {
        childRepository.deleteById(childId);
    }

    @Transactional
    public void deleteParent(Long parentId) {
        parentRepository.deleteById(parentId);
    }
}
