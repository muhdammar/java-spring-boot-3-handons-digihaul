package com.example.javaspringboot3handonsdigihaul;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow( () -> new IllegalAccessError("Id not found"));
    }

    public void deleteSoftwareEngineerById(Integer id) {
        softwareEngineerRepository.deleteById(id);
    }

    public void updateSoftwareEngineer(SoftwareEngineer softwareEngineer, Integer id) {
        SoftwareEngineer swe = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "SoftwareEngineer with id " + id + " does not exist"));

        if (softwareEngineer == null) {
            throw new IllegalArgumentException("softwareEngineer must not be null");
        }

        // update fields (only if provided)
        if (softwareEngineer.getName() != null) {
            swe.setName(softwareEngineer.getName());
        }
        if (softwareEngineer.getTechStack() != null) {
            swe.setTechStack(softwareEngineer.getTechStack());
        }

        softwareEngineerRepository.save(swe);
    }
}
