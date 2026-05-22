package com.example.javaspringboot3handonsdigihaul.controller;

import com.example.javaspringboot3handonsdigihaul.entity.SoftwareEngineer;
import com.example.javaspringboot3handonsdigihaul.service.SoftwareEngineerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getEngineers(){
        return softwareEngineerService.getSoftwareEngineers();
    }
    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @GetMapping("{id}")
    public SoftwareEngineer getEngineerById(@PathVariable Integer id){
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @DeleteMapping("{id}")
    public void deleteEngineerById(@PathVariable Integer id){
       softwareEngineerService.deleteSoftwareEngineerById(id);
    }

    @PutMapping({"{id}"})
    public void updateSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer, @PathVariable Integer id){
        softwareEngineerService.updateSoftwareEngineer(softwareEngineer, id);
    }


}
