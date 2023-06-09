package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepos;
    private final ModelMapper modelMapper;

    public TreatmentService(TreatmentRepository treatmentRepos, ModelMapper modelMapper) {
        this.treatmentRepos = treatmentRepos;
        this.modelMapper = modelMapper;
    }

    public Long createTreatment(TreatmentDto tDto) {
        Treatment treatment = transferDtoToTreatment(tDto);
        treatmentRepos.save(treatment);
        return treatment.getId();
    }

    public List<TreatmentDto> getAllTreatments() {
        Iterable<Treatment> treatments = treatmentRepos.findAll();
        List<TreatmentDto> treatmentDtoList = new ArrayList<>();
        for (Treatment treat : treatments) {
            treatmentDtoList.add(transferTreatmentToDto(treat));
        }
        return treatmentDtoList;
    }

    public void updateTreatment(Long id, TreatmentDto treatmentForUpdate) {
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
        if (optionalTreatment.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            Treatment existingTreatment = optionalTreatment.get();
            Treatment treatmentToBeSaved = transferDtoToTreatment(treatmentForUpdate);
            treatmentToBeSaved.setId(existingTreatment.getId());
            treatmentRepos.save(treatmentToBeSaved);
        }
    }

    public void deleteTreatment(Long id) {
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
        if (optionalTreatment.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            treatmentRepos.deleteById(id);
        }
    }

    public Integer calculateCombinedDuration(List<Long> treatmentIds) {
        List<Treatment> treatmentList = treatmentRepos.findAllByIdIsIn(treatmentIds);
        int combinedDuration = 0;
        for (Treatment treat : treatmentList) {
            combinedDuration = combinedDuration + treat.getDurationInMinutes();
        }
        return combinedDuration;
    }

    public Treatment transferDtoToTreatment(TreatmentDto tDto) {
        return modelMapper.map(tDto, Treatment.class);
    }

    public TreatmentDto transferTreatmentToDto(Treatment treatment) {
        TreatmentDto treatmentDto = modelMapper.map(treatment, TreatmentDto.class);
        return treatmentDto;
    }

}
