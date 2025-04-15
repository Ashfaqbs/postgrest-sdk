package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ashfaq.pgrestsdk.PgRestSdk;

import com.example.demo.dto.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final PgRestSdk pgRestSdk;

    @Autowired
    public EmployeeController(PgRestSdk pgRestSdk) {
        this.pgRestSdk = pgRestSdk;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return pgRestSdk.create("employee", employee, Employee.class);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id) {
      return  pgRestSdk.read("employee", id, Employee[].class);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        pgRestSdk.update("employee", id, employee, Employee.class);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        pgRestSdk.delete("employee", id);
    }










    @GetMapping("/param")
    public ResponseEntity<List<Employee>> getEmployeesByParam(@RequestParam Map<String, String> params) {
        List<Employee> employees = pgRestSdk.findByParams("employee", params, Employee.class);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/fetchAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = pgRestSdk.findAll("employee", Employee.class);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/fetchAllByL0")
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "3") int limit,
            @RequestParam(defaultValue = "2") int offset) {
        List<Employee> employees = pgRestSdk.findAllbyLimitandOffset("employee", limit, offset, Employee.class);
        return ResponseEntity.ok(employees);
    }













}


