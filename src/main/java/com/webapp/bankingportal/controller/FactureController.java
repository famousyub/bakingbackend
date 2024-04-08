package com.webapp.bankingportal.controller;


import com.webapp.bankingportal.entity.Facture;
import com.webapp.bankingportal.service.FactureService;
import com.webapp.bankingportal.service.MyFactureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/facture")
public class FactureController {



     @Autowired
    private FactureService factureService;

     @Autowired
    private MyFactureService myFactureService;



    @GetMapping
    public List<Facture> getAllEmployees() {
        return myFactureService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Optional<Facture> employee = myFactureService.getEmployeeById(employeeId);
        if (employee.isPresent()) {
            return ResponseEntity.ok().body(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public Facture createEmployee(@Valid @RequestBody Facture employee) {
        return myFactureService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facture> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Facture employeeDetails) {
        Optional<Facture> optionalEmployee = myFactureService.updateEmployee(employeeId, employeeDetails);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        boolean deleted = myFactureService.deleteEmployee(employeeId);
        if (deleted) {
            return ResponseEntity.ok().body("deleted succesfully");
        } else {
            return ResponseEntity.badRequest().body("Employee not found or could not be deleted.");
        }
    }




}
