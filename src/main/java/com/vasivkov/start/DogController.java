package com.vasivkov.start;

import com.vasivkov.exception.DogNotFoundException;
import com.vasivkov.start.domain.Dog;
import com.vasivkov.start.repos.DogRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DogController {
    @Autowired
    private DogRepos dogRepos;

    @GetMapping(value = "/dogs")
    public Iterable<Dog> main() {

        return dogRepos.findAll();
    }

    @PostMapping(value = "/dog")
    public Dog add(@RequestParam String name, @RequestParam int weight) {
        Dog dog = new Dog(name, weight);
        if(!dogRepos.findByNameAndWeight(name, weight).equals(dog)) {
            dogRepos.save(dog);
        }
        Iterable<Dog> dogs = dogRepos.findAll();
        return dog;
    }

    @GetMapping("/dog/{dogsId}")
    public Dog getNoteById(@PathVariable int dogsId) throws DogNotFoundException {
        return dogRepos.findById(dogsId).orElseThrow(() -> new DogNotFoundException(dogsId));
    }

    @DeleteMapping("/dog/del/{dogsId}")
    public ResponseEntity deleteDog(@PathVariable int dogsId) throws DogNotFoundException {
        Dog dog = dogRepos.findById(dogsId)
                .orElseThrow(() -> new DogNotFoundException(dogsId));

        dogRepos.delete(dog);
        return ResponseEntity.ok().build();
    }
    @PutMapping("dog/change/{dogsId}")
    public Dog changeDog (@PathVariable int dogsId, @RequestParam String name, @RequestParam int weight) throws DogNotFoundException {
        Dog dog = dogRepos.findById(dogsId).orElseThrow(() -> new DogNotFoundException(dogsId));
        dog.setName(name);
        dog.setWeight(weight);
        dogRepos.save(dog);

        return dog;
    }


}

