package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.BillInputDto;
import nl.novi.kapsalon.dtos.BillOutputDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Bill;
import nl.novi.kapsalon.models.Product;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.BillRepository;
import nl.novi.kapsalon.repositories.ProductRepository;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import nl.novi.kapsalon.repositories.UserRepository;
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
    private final UserRepository userRepos;
    private final ModelMapper modelMapper;


    public BillService(BillRepository billRepos, TreatmentRepository treatmentRepos, ProductRepository productRepos, UserRepository userRepos, ModelMapper modelMapper) {
        this.billRepos = billRepos;
        this.treatmentRepos = treatmentRepos;
        this.productRepos = productRepos;
        this.userRepos = userRepos;
        this.modelMapper = modelMapper;
    }

    public BillOutputDto createBill(BillInputDto bDto) {
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

        List<Long> treatmentIds = bDto.getTreatmentIds();
        double totalPrice = calculateAmountOnBill(treatmentIds);

        BillOutputDto boDto = convertBillToOutputDto(bill);
        boDto.setTotalPrice(totalPrice);

        return boDto;
    }

    public BillInputDto getBill(Long id) {
        Bill bill = billRepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Men kan geen kaalkop bij het haar vatten: de id van deze bon staat niet in het systeem."));
        return transferBillToDto(bill);
    }

    public void updateBill(Long id, BillInputDto billForUpdate) {
        Optional<Bill> optionalBill = billRepos.findById(id);
        if (optionalBill.isEmpty()) {
            throw new ResourceNotFoundException("We zitten met de handen in het haar, maar we kunnen het id van deze rekening niet vinden. Probeer het nog eens met een ander id.");
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
            throw new ResourceNotFoundException("We hebben de rekening opgemaakt, maar de id van deze bon staat niet in het systeem.");
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
            throw new ResourceNotFoundException("We willen je niet van hot naar haar sturen, maar het rekening-id en/of het product-id staan niet in het systeem. Check nog even goed of je de nummers goed hebt ingevuld.");
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
            throw new ResourceNotFoundException("We hebben alles op haren en snaren gezet, maar kunntn het rekening-id en/of het behandelings-id niet vinden. Probeer het nog eens.");
        }
    }

    public double calculateAmountOnBill(List<Long> treatmentIds) {
        List<Treatment> treatmentList = treatmentRepos.findAllByIdIsIn(treatmentIds);

        double totalPrice = 0;
        for (Treatment treat : treatmentList) {
            totalPrice = totalPrice + treat.getPrice();
        }

        return totalPrice;
    }


    public Bill transferDtoToBill(BillInputDto billDto) {
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

    public BillInputDto transferBillToDto(Bill bill) {
        BillInputDto billDto = modelMapper.map(bill, BillInputDto.class);

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

    private BillOutputDto convertBillToOutputDto(Bill bill) {
        BillOutputDto boDto = modelMapper.map(bill, BillOutputDto.class);

        User customer = userRepos.findById(bill.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Klant onbekend"));
        User hairdresser = userRepos.findById(bill.getHairdresserId())
                .orElseThrow(() -> new ResourceNotFoundException("Kapster onbekend"));

        String customerFullName = customer.getFirstName() + " " + customer.getLastName();
        String hairdresserFullName = hairdresser.getFirstName() + " " + hairdresser.getLastName();

        boDto.setCustomerName(customerFullName);
        boDto.setHairdresserName(hairdresserFullName);

        return boDto;
    }
}
