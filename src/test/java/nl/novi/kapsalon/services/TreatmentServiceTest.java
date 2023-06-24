package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TreatmentServiceTest {

    @Mock
    TreatmentRepository treatmentRepos;

    @InjectMocks
    TreatmentService treatmentService;

    @Captor
    ArgumentCaptor<Treatment> treatmentArgumentCaptor;

    Treatment treat1;
    Treatment treat2;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        treatmentService = new TreatmentService(treatmentRepos, modelMapper);

        Long treatmentId1 = 1L;
        Long treatmentId2 = 2L;

        treat1 = new Treatment("Knippen wassen", 25, 25.00);
        treat1.setId(treatmentId1);

        treat2 = new Treatment("Haar vlechten", 30, 30.00);
        treat2.setId(treatmentId2);
    }

    @Test
    @DisplayName("Should save correct newly created treatment")
    void createTreatment() {
        when(treatmentRepos.save(treat1)).thenReturn(treat1);

        treatmentService.createTreatment(treatmentService.transferTreatmentToDto(treat1));
        verify(treatmentRepos, times(1)).save(treatmentArgumentCaptor.capture());
        Treatment savedTreatment = treatmentArgumentCaptor.getValue();

        assertEquals(treat1.getId(), savedTreatment.getId());
        assertEquals(treat1.getName(), savedTreatment.getName());
        assertEquals(treat1.getDurationInMinutes(), savedTreatment.getDurationInMinutes());
        assertEquals(treat1.getPrice(), savedTreatment.getPrice());
    }

    @Test
    @DisplayName("Should return all treatments in the database")
    void getAllTreatments() {
        when(treatmentRepos.findAll()).thenReturn(List.of(treat1, treat2));

        List<Treatment> treatments = treatmentRepos.findAll();
        List<TreatmentDto> treatmentDtoList = treatmentService.getAllTreatments();

        for (int i = 0; i < treatmentDtoList.size(); i++) {
            Treatment treat = treatments.get(i);
            TreatmentDto tDto = treatmentDtoList.get(i);

            assertEquals(treat.getId(), tDto.getId());
            assertEquals(treat.getName(), tDto.getName());
            assertEquals(treat.getDurationInMinutes(), tDto.getDurationInMinutes());
            assertEquals(treat.getPrice(), tDto.getPrice());
        }
    }

    @Test
    @DisplayName("Should update treatment information in the databased based on id")
    void updateTreatment() {
        when(treatmentRepos.findById(1L)).thenReturn(Optional.of(treat1));

        TreatmentDto newTreatmentDto = new TreatmentDto("Knippen wassen", 25, 26.00);
        newTreatmentDto.setId(treat1.getId());

        when(treatmentRepos.save(treatmentService.transferDtoToTreatment(newTreatmentDto))).thenReturn(treat1);
        treatmentService.updateTreatment(1L, newTreatmentDto);
        verify(treatmentRepos, times(1)).save(treatmentArgumentCaptor.capture());
        Treatment captured = treatmentArgumentCaptor.getValue();

        assertEquals(newTreatmentDto.getId(), captured.getId());
        assertEquals(newTreatmentDto.getName(), captured.getName());
        assertEquals(newTreatmentDto.getDurationInMinutes(), captured.getDurationInMinutes());
        assertEquals(newTreatmentDto.getPrice(), captured.getPrice());
    }

    @Test
    void deleteTreatment() {
    }

    @Test
    void calculateCombinedDuration() {
    }
}