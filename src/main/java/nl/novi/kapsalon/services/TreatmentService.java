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
    private ModelMapper modelMapper;

    public TreatmentService(TreatmentRepository treatmentRepos, ModelMapper modelMapper) {
        this.treatmentRepos = treatmentRepos;
        this.modelMapper = modelMapper;
    }

    public Integer createTreatment(TreatmentDto tDto) {
        Treatment treatment = new Treatment();
        treatment = transferDtoToTreatment(treatment, tDto);
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

    public void updateTreatment(Integer id, TreatmentDto treatmentForUpdate) {
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
        if (optionalTreatment.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            Treatment existingTreatment = optionalTreatment.get();
            Treatment treatmentToBeSaved = transferDtoToTreatment(existingTreatment, treatmentForUpdate);
            treatmentRepos.save(treatmentToBeSaved);
        }
    }

    public void deleteTreatment(Integer id) {
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
        if (optionalTreatment.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            treatmentRepos.deleteById(id);
        }
    }

    public Integer calculateCombinedDuration(List<Integer> treatmentIds) {
        List<Treatment> treatmentList = treatmentRepos.findAllByIdIn(treatmentIds);
        int combinedDuration = 0;
        for (Treatment treat : treatmentList) {
            combinedDuration = combinedDuration + treat.getDurationInMinutes();
        } return combinedDuration;
    }

    public Treatment transferDtoToTreatment(Treatment treat, TreatmentDto tDto) {
        modelMapper.map(tDto, treat);
        return treat;
    }

//    public Treatment transferDtoToTreatment(Treatment treat, TreatmentDto tdto) {
//        treat.setName(tdto.name);
//        treat.setDurationInMinutes(tdto.durationInMinutes);
//        treat.setPrice(tdto.price);
//        treatmentRepos.save(treat);
//        return treat;
//    }

    public TreatmentDto transferTreatmentToDto(Treatment treatment) {
        TreatmentDto treatmentDto = modelMapper.map(treatment, TreatmentDto.class);
        return treatmentDto;
    }

//    public TreatmentDto transferTreatmentToDto(Treatment treatment) {
//        TreatmentDto treatmentDto = new TreatmentDto();
//        treatmentDto.id = treatment.getId();
//        treatmentDto.name = treatment.getName();
//        treatmentDto.durationInMinutes = treatment.getDurationInMinutes();
//        treatmentDto.price = treatment.getPrice();
//        return treatmentDto;
//    }
}
