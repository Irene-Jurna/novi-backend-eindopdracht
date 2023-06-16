package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.BillDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Bill;
import nl.novi.kapsalon.models.Product;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.BillRepository;
import nl.novi.kapsalon.repositories.ProductRepository;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepos;
    private final TreatmentRepository treatmentRepos;
    private final ProductRepository productRepos;
    private final ModelMapper modelMapper;


    public BillService(BillRepository billRepos, TreatmentRepository treatmentRepos, ProductRepository productRepos, ModelMapper modelMapper) {
        this.billRepos = billRepos;
        this.treatmentRepos = treatmentRepos;
        this.productRepos = productRepos;
        this.modelMapper = modelMapper;
    }

    public Long createBill(BillDto bDto) {
        Bill bill = transferDtoToBill(bDto);

        List<Product> productList = productRepos.findAllByIdIn(bDto.productIds);
        bill.setProducts(productList);

        List<Treatment> treatmentList = treatmentRepos.findAllByIdIsIn(bDto.treatmentIds);
        bill.setTreatments(treatmentList);

        bill = billRepos.save(bill);

        for (Long productId : bDto.getProductIds()) {
            assignProductToBill(bill.getId(), productId);
        }

        for (Long treatmentId : bDto.getTreatmentIds()) {
            assignTreatmentToBill(bill.getId(), treatmentId);
        }

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

    public void assignProductToBill(Long billId, Long productId) {
        Optional<Bill> optionalBill = billRepos.findById(billId);
        Optional<Product> optionalProduct = productRepos.findById(productId);

        if (optionalBill.isPresent() && optionalProduct.isPresent()) {
            Bill bill = optionalBill.get();
            Product product = optionalProduct.get();

            if (!bill.getProducts().contains(product)) {
                List<Product> productList = bill.getProducts();
                productList.add(product);
                bill.setProducts(productList);

                billRepos.save(bill);
            }
        } else {
            throw new ResourceNotFoundException("Het rekening-id en/of het product-id staan niet in het systeem");
        }
    }

    public void assignTreatmentToBill(Long billId, Long treatmentId) {
        Optional<Bill> optionalBill = billRepos.findById(billId);
        Optional<Treatment> optionalTreatment = treatmentRepos.findById(treatmentId);

        if (optionalBill.isPresent() && optionalTreatment.isPresent()) {
            Bill bill = optionalBill.get();
            Treatment treatment = optionalTreatment.get();

            if (!bill.getTreatments().contains(treatment)) {
                List<Treatment> treatmentList = bill.getTreatments();
                treatmentList.add(treatment);
                bill.setTreatments(treatmentList);

                billRepos.save(bill);
            }
        } else {
            throw new ResourceNotFoundException("Het rekening-id en/of het behandelings-id staan niet in het systeem");
        }
    }


    public Bill transferDtoToBill(BillDto billDto) {
        Bill bill = modelMapper.map(billDto, Bill.class);

        if (billDto.productIds != null) {
            List<Product> products = productRepos.findAllByIdIn(billDto.getProductIds());
            bill.setProducts(products);
        }

        if (billDto.treatmentIds != null) {
            List<Treatment> treatments = treatmentRepos.findAllByIdIsIn(billDto.getTreatmentIds());
            bill.setTreatments(treatments);
        }

        return bill;
    }

    public BillDto transferBillToDto(Bill bill) {
        BillDto billDto = modelMapper.map(bill, BillDto.class);

        List<Long> treatmentIds = new ArrayList<>();
        for (Treatment treatment : bill.getTreatments()) {
            treatmentIds.add(treatment.getId());
        }
        billDto.setTreatmentIds(treatmentIds);

        List<Long> productIds = new ArrayList<>();
        for (Product product : bill.getProducts()) {
            productIds.add(product.getId());
        }
        billDto.setProductIds(productIds);

        return billDto;
    }

}
