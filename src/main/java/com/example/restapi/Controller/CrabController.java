package com.example.restapi.Controller;

import com.example.restapi.Entity.Crab;
import com.example.restapi.Entity.Food;
import com.example.restapi.Exceptions.ElementNotFoundException;
import com.example.restapi.Repository.CrabRepository;
import com.example.restapi.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CrabController {

    @Autowired
    CrabRepository crabRepository;

    @Autowired
    FoodRepository foodRepository;


    @GetMapping("/crabs")
    public List<Crab> getCrabs()
    {
        return crabRepository.findAll();
    }

    @GetMapping("/foods")
    public List<Food> getFoods()
    {
        return foodRepository.findAll();
    }

    @GetMapping("/crabs/{id}")
    public Crab getCrab(@PathVariable Integer id)
    {
        Crab crab= crabRepository.findById(id).orElse(null);

        if(crab==null)
            throw new ElementNotFoundException(id.toString());

        return crab;
    }

    @GetMapping("/crabs/{id}/foods")
    public List<Food> getCrabFood(@PathVariable Integer id)
    {
        Crab crab= crabRepository.findById(id).orElse(null);

        if(crab==null)
            throw new ElementNotFoundException(id.toString());

        return crab.getFoodList();
    }

    @GetMapping("/foods/{id}")
    public Food getFood(@PathVariable Integer id)
    {
        Food food= foodRepository.findById(id).orElse(null);

        if(food==null)
            throw new ElementNotFoundException(id.toString());

        return food;
    }

    @PostMapping("/crabs/add")
    public ResponseEntity<Object> addCrab(@Valid @RequestBody Crab _crab)
    {
        Crab crab=crabRepository.save(_crab);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/crabs/{id}")
                .buildAndExpand(crab.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/crabs/{id}/add_food")
    public void addCrabFood(@Valid @RequestBody Food food, @PathVariable Integer id)
    {
        Crab crab=crabRepository.getById(id);

        if(crab==null)
            throw new  ElementNotFoundException(id.toString());

        food.getCrabs().add(crab);
        foodRepository.save(food);
        crab.getFoodList().add(food);
        crabRepository.save(crab);
    }

    @PostMapping("/crabs/delete/{id}")
    public void deleteCrab(@PathVariable Integer id)
    {
        crabRepository.deleteById(id);
    }

    @PostMapping("/foods/delete/{id}")
    public void deleteFood(@PathVariable Integer id)
    {
        foodRepository.deleteById(id);
    }

}
