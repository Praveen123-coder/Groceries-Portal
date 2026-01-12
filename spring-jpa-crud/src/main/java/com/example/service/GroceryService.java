package com.example.service;

import com.example.entity.Grocery;
import com.example.repository.GroceryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {

    private final GroceryRepository repo;

    public GroceryService(GroceryRepository repo) {
        this.repo = repo;
    }

   
    public List<Grocery> getAll() {
        return repo.findAll();
    }

    
    public Grocery getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    
    public Grocery create(Grocery grocery) {
        return repo.save(grocery);
    }

  
    public Grocery update(Long id, Grocery updated) {
        return repo.findById(id).map(g -> {
            g.setItem(updated.getItem());
            g.setCategory(updated.getCategory());
            g.setQuantity(updated.getQuantity());
            g.setPrice(updated.getPrice());
            g.setDiscount(updated.getDiscount());
            g.setLast(updated.getLast());
            return repo.save(g);
        }).orElse(null);
    }

    
    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
