package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.BillDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Bill;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.BillRepository;
import nl.novi.kapsalon.repositories.ProductRepository;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import nl.novi.kapsalon.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepos;
    private final UserRepository userRepos;
    private final TreatmentRepository treatmentRepos;
    private final ProductRepository productRepos;
    private final ModelMapper modelMapper;


    public BillService(BillRepository billRepos, UserRepository userRepos, TreatmentRepository treatmentRepos, ProductRepository productRepos, ModelMapper modelMapper) {
        this.billRepos = billRepos;
        this.userRepos = userRepos;
        this.treatmentRepos = treatmentRepos;
        this.productRepos = productRepos;
        this.modelMapper = modelMapper;
    }

    public Long createBill(BillDto bDto) {
        Bill bill = transferDtoToBill(bDto);

        // Happy flow
//        User hairdresser = userRepos.findById(bDto.hairdresserId).get();
        List<Treatment> treatmentList = treatmentRepos.findAllByIdIsIn(bDto.treatments);
        bill.setTreatments(treatmentList);

        billRepos.save(bill);
        return bill.getId();
    }

    public BillDto getBill(Long id) {
        Bill bill = billRepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bon niet gevonden"));
        return transferBillToDto(bill);
    }

    public void updateBill(Long id, BillDto billForUpdate) {
        Optional<Bill> optionalBill = billRepos.findById(id);
        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("Dit behandel-id staat niet in het systeem");
        } else {
            Bill existingBill = optionalBill.get();
            Bill billToBeSaved = transferDtoToBill(billForUpdate);
            billToBeSaved.setId(existingBill.getId());
            billRepos.save(billToBeSaved);
        }
    }

    public void deleteBill(Long id) {
        Optional<Bill> optionalBill = billRepos.findById(id);
        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("De id van deze bon staat niet in het systeem");
        } else {
            billRepos.deleteById(id);
        }
    }

    public Bill transferDtoToBill(BillDto bDto) {
        return modelMapper.map(bDto, Bill.class);
    }

    public BillDto transferBillToDto(Bill bill) {
        return modelMapper.map(bill, BillDto.class);
    }
}
