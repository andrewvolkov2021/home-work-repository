package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditRestController {

    private final IEntityService<Audit> auditService;

    public AuditRestController(IEntityService<Audit> auditService){
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<?> createAudit(@RequestBody Audit audit){
        try {
            auditService.createEntity(audit);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAudit(@PathVariable("id") Long id,
                                         @RequestBody Audit audit) {
        try {
            auditService.updateEntity(audit, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAudit(@PathVariable("id") Long id) {
        try {
            auditService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Audit>> getAllAudits(@RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("name") String name){
        List<Audit> audits = auditService.getAllEntities();
        return new ResponseEntity<>(audits, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Audit> getAudit(@PathVariable("id") Long id) {
        try {
            Audit audit = auditService.getEntity(id);
            return new ResponseEntity<>(audit, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
