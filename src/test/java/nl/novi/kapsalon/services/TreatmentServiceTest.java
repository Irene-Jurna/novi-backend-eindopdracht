package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
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

import java.util.*;

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
    Treatment treat3;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        treatmentService = new TreatmentService(treatmentRepos, modelMapper);

        Long treatmentId1 = 1L;
        Long treatmentId2 = 2L;

        treat1 = new Treatment("Knippen wassen", 25, 25.00);
        treat1.setId(treatmentId1);
        treat1.setName("Knippen wassen");
        treat1.setDurationInMinutes(25);
        treat1.setPrice(25.00);

        treat2 = new Treatment("Haar vlechten", 30, 30.00);
        treat2.setId(treatmentId2);
        treat2.setName("Haar vlechten");
        treat2.setDurationInMinutes(30);
        treat2.setPrice(30.00);
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
    @DisplayName("Should return exception for update treatment method because id does not exists")
    void updateTreatmentException() {
        TreatmentDto newTreatmentDto = new TreatmentDto("Blonderen", 60, 60.00);
        newTreatmentDto.setId(treat1.getId());
        assertThrows(ResourceNotFoundException.class, () -> treatmentService.updateTreatment(null, newTreatmentDto));
    }

    @Test
    @DisplayName("Should delete correct treatment from database based on id")
    void deleteTreatment() {
        when(treatmentRepos.findById(treat2.getId())).thenReturn(Optional.of(treat2));
        treatmentService.deleteTreatment(2L);
        verify(treatmentRepos).deleteById(2L);
    }

    @Test
    @DisplayName("Should not delete treatment because the id does not exists")
    void deleteProductException() {
        when(treatmentRepos.findById(treat1.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            treatmentService.deleteTreatment(3L);
        });
        assertEquals("Het is haarscherp: dit behandel-id staat niet in het systeem", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate combined duration of treatments")
    void calculateCombinedDuration() {

        List<Long> treatmentIds = Arrays.asList(1L, 2L);
        List<Treatment> treatmentList = List.of(treat1, treat2);

        when(treatmentRepos.findAllByIdIsIn(treatmentIds)).thenReturn(treatmentList);

        Integer combinedDuration = treatmentService.calculateCombinedDuration(treatmentIds);
        assertEquals(combinedDuration, 55);
    }

    @Test
    @DisplayName("Method to calculate combined duration should return exception message")
    void calculateCombinedDurationException() {
        List<Long> treatmentIds = Arrays.asList(1L, 2L, 3L);
        List<Treatment> treatmentList = new ArrayList<>(List.of(treat1, treat2));

        when(treatmentRepos.findAllByIdIsIn(treatmentIds)).thenReturn(treatmentList);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
        treatmentService.calculateCombinedDuration(treatmentIds);
    });

        assertEquals("Daar hangt de schaar uit: behandel-id " + 3 + " staat niet in het systeem. De behandeltijd van de resterende behandelingen is: " + 55 + " minuten." , exception.getMessage());
    }
}