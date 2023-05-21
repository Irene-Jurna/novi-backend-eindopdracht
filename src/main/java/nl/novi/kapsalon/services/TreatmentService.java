package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.dtos.TreatmentRequestDto;
import nl.novi.kapsalon.exceptions.BusinessException;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TreatmentService implements CrudService<TreatmentRequestDto, TreatmentDto, Treatment>{
    private final TreatmentRepository treatmentRepos;
//    private final ModelMapper modelMapper;

    @Override
    public TreatmentDto create(TreatmentRequestDto treatmentRequestDto) throws BusinessException {
        Function<TreatmentRequestDto, Treatment> converterFunction = req -> Treatment.builder()
                .name(req.getName())
                .durationInMinutes(req.getDurationInMinutes())
                .price(req.getPrice())
                .build();
        Treatment treatment = converterFunction.apply(treatmentRequestDto);
        return toResponse(treatmentRepos.save(treatment));
    }

    @Override
    public List<TreatmentDto> getAll() throws BusinessException {
        Function<Treatment, TreatmentDto> function = this::toResponse;
        return treatmentRepos.findAll().stream().map(function).collect(Collectors.toList());
    }

    @Override
    public TreatmentDto get(Long id) throws BusinessException {
        return null;
    }

    @Override
    public TreatmentDto update(Long id, TreatmentRequestDto treatmentRequestDto) throws BusinessException {
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
        if (optionalTreatment.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            Function<TreatmentRequestDto, Treatment> converterFunction = req -> Treatment.builder()
                    .name(req.getName())
                    .durationInMinutes(req.getDurationInMinutes())
                    .price(req.getPrice())
                    .build();
            Treatment treatment = converterFunction.apply(treatmentRequestDto);
            treatment.setId(id);
            return toResponse(treatmentRepos.save(treatment));
        }
    }

    @Override
    public void delete(Long id) throws BusinessException {
        treatmentRepos.findById(id).ifPresentOrElse(treatment -> treatmentRepos.deleteById(id), () -> {
            throw new BusinessException("Not found", HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public Treatment toEntity(TreatmentRequestDto treatmentRequestDto) {
        return null;
    }

    @Override
    public TreatmentDto toResponse(Treatment entity) {
        return TreatmentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .durationInMinutes(entity.getDurationInMinutes())
                .price(entity.getPrice())
                .build();
    }

    public TreatmentService(TreatmentRepository treatmentRepos, ModelMapper modelMapper) {
        this.treatmentRepos = treatmentRepos;
//        this.modelMapper = modelMapper;
    }
//
//    public Long createTreatment(TreatmentDto tDto) {
//        Treatment treatment = new Treatment();
//        treatment = transferDtoToTreatment(treatment, tDto);
//        return treatment.getId();
//    }

//    public List<TreatmentDto> getAllTreatments() {
//        Iterable<Treatment> treatments = treatmentRepos.findAll();
//        List<TreatmentDto> treatmentDtoList = new ArrayList<>();
//        for (Treatment treat : treatments) {
//            treatmentDtoList.add(transferTreatmentToDto(treat));
//        }
//        return treatmentDtoList;
//    }

//    public void updateTreatment(Long id, TreatmentDto treatmentForUpdate) {
//        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
//        if (optionalTreatment.isEmpty()) {
//            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
//        } else {
//            Treatment existingTreatment = optionalTreatment.get();
//            Treatment treatmentToBeSaved = transferDtoToTreatment(existingTreatment, treatmentForUpdate);
//            treatmentRepos.save(treatmentToBeSaved);
//        }
//    }

    public void deleteTreatment(Long id) {
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(id);
        if (optionalTreatment.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            treatmentRepos.deleteById(id);
        }
    }

    public Integer calculateCombinedDuration(List<Long> treatmentIds) {
        List<Treatment> treatmentList = treatmentRepos.findAllByIdIn(treatmentIds);
        int combinedDuration = 0;
        for (Treatment treat : treatmentList) {
            combinedDuration = combinedDuration + treat.getDurationInMinutes();
        }
        return combinedDuration;
    }

//    public Treatment transferDtoToTreatment(Treatment treat, TreatmentDto tDto) {
//        treat = modelMapper.map(tDto, Treatment.class);
//        treatmentRepos.save(treat);
//        return treat;
//    }

//    public Treatment transferDtoToTreatment(Treatment treat, TreatmentDto tdto) {
//        treat.setName(tdto.name);
//        treat.setDurationInMinutes(tdto.durationInMinutes);
//        treat.setPrice(tdto.price);
//        treatmentRepos.save(treat);
//        return treat;
//    }

//    public TreatmentDto transferTreatmentToDto(Treatment treatment) {
//        TreatmentDto treatmentDto = modelMapper.map(treatment, TreatmentDto.class);
//        treatmentDto.setName(treatment.getName());
//        return treatmentDto;
//    }

//    public TreatmentDto transferTreatmentToDto(Treatment treatment) {
//        TreatmentDto treatmentDto = new TreatmentDto();
//        treatmentDto.id = treatment.getId();
//        treatmentDto.name = treatment.getName();
//        treatmentDto.durationInMinutes = treatment.getDurationInMinutes();
//        treatmentDto.price = treatment.getPrice();
//        return treatmentDto;
//    }
}
