package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.BillDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Bill;
import nl.novi.kapsalon.repositories.BillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepos;
    private final ModelMapper modelMapper;


    public BillService(BillRepository billRepos, ModelMapper modelMapper) {
        this.billRepos = billRepos;
        this.modelMapper = modelMapper;
    }

    public Long createBill(BillDto bDto) {
        Bill bill = transferDtoToBill(bDto);
        billRepos.save(bill);
        return bill.getId();
    }

    public BillDto getBill(Long id) {
        Bill bill = billRepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bon niet gevonden"));
        return transferBillToDto(bill);
    }

    public Bill transferDtoToBill(BillDto bDto) {
        return modelMapper.map(bDto, Bill.class);
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

    public BillDto transferBillToDto(Bill bill) {
        return modelMapper.map(bill, BillDto.class);
    }
}
