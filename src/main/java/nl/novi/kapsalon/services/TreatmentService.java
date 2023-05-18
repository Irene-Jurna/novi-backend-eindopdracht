package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepos;

    public TreatmentService(TreatmentRepository treatmentRepos) {
        this.treatmentRepos = treatmentRepos;
    }

    public Long createTreatment(TreatmentDto tDto) {
        Treatment treatment = new Treatment();
        treatment = transferDtoToTreatment(treatment, tDto);
        return treatment.getId();
    }

    public Treatment transferDtoToTreatment(Treatment treat, TreatmentDto tdto) {
        treat.setName(tdto.name);
        treat.setDurationInMinutes(tdto.durationInMinutes);
        treat.setPrice(tdto.price);
        treatmentRepos.save(treat);
        return treat;
    }
}
