package de.mrtimeey.secretsanta.group.rest.controller;

import de.mrtimeey.secretsanta.group.rest.request.OnCreate;
import de.mrtimeey.secretsanta.group.rest.request.OnUpdate;
import de.mrtimeey.secretsanta.group.rest.response.PersonTO;
import de.mrtimeey.secretsanta.group.rest.service.GroupRestService;
import de.mrtimeey.secretsanta.group.rest.service.PersonRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Validated
public class PersonController {

    private final PersonRestService personRestService;
    private final GroupRestService groupRestService;

    @PostMapping
    @Validated(OnCreate.class)
    public ResponseEntity<PersonTO> createPerson(@RequestBody @Valid PersonTO personTO) {
        if (!groupRestService.groupExisting(personTO.getSecretSantaGroupId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personRestService.create(personTO));
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<PersonTO> getPerson(@PathVariable String personId) {
        return personRestService.getPerson(personId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{personId}")
    public void deletePerson(@PathVariable String personId) {
        personRestService.deletePerson(personId);
    }

    @PutMapping
    @Validated(OnUpdate.class)
    public ResponseEntity<PersonTO> updatePerson(@RequestBody PersonTO personTO) {
        return personRestService.updatePerson(personTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}