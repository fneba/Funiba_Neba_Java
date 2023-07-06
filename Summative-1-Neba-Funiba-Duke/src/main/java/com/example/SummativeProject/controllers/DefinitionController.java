package com.example.SummativeProject.controllers;

import com.example.SummativeProject.models.Definition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/word")
@ResponseStatus(value = HttpStatus.OK)
public class DefinitionController {
    private List<Definition> definitions;

    public DefinitionController() {
        definitions = new ArrayList<>();
        definitions.add(createDefinition(1, "Apple", "A round fruit with red or green skin."));
        definitions.add(createDefinition(2, "Dog", "A domesticated carnivorous mammal."));
        definitions.add(createDefinition(3, "Book", "A written or printed work consisting of pages glued or sewn together along one side."));
        definitions.add(createDefinition(4, "Sun", "The star around which the earth orbits."));
        definitions.add(createDefinition(5, "Computer", "An electronic device that can perform various tasks."));
        definitions.add(createDefinition(6, "Ocean", "A large body of saltwater."));
        definitions.add(createDefinition(7, "Music", "An art form that uses sound and rhythm."));
        definitions.add(createDefinition(8, "Mountain", "A large natural elevation of the earth's surface."));
        definitions.add(createDefinition(9, "Coffee", "A hot drink made from roasted coffee beans."));
        definitions.add(createDefinition(10, "Rainbow", "A meteorological phenomenon that is caused by reflection, refraction, and dispersion of light."));
    }

    @GetMapping
    public Definition getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(definitions.size());
        return definitions.get(randomIndex);
    }

    // Other methods and definitions

    private Definition createDefinition(int id, String word, String definition) {
        Definition definitionObj = new Definition();
        definitionObj.setId(id);
        definitionObj.setWord(word);
        definitionObj.setDefinition(definition);
        return definitionObj;
    }
}
