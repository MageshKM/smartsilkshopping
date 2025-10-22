package com.example.jpademo;

import com.example.jpademo.model.Child;
import com.example.jpademo.model.Parent;
import com.example.jpademo.repository.ParentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ParentRepository parentRepository;

    public DataLoader(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Parent p = new Parent("Alice");
        p.addChild(new Child("Bob"));
        p.addChild(new Child("Charlie"));
        parentRepository.save(p);

        Parent p2 = new Parent("Dina");
        p2.addChild(new Child("Evan"));
        parentRepository.save(p2);

        System.out.println("Seeded parents and children");
    }
}
