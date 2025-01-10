package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.AdditionalExpensesRequest;
import dk.mpb.manage.project.service.AdditionalExpensesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/additionalexpenses")
public class AdditionalExpensesController {
    private AdditionalExpensesService additionalExpensesService;

    public AdditionalExpensesController(AdditionalExpensesService additionalExpensesService) {
        this.additionalExpensesService = additionalExpensesService;
    }

    /**
     * Get all additional expenses for a reservation
     * */
    @GetMapping
    public void getAllAdditionalExpenses(@RequestParam int reservationId) {
        additionalExpensesService.getAllAdditionalExpenses(reservationId);
    }

    /**
     * Add additional expenses to a reservation
     * */
    @PostMapping
    public void addAdditionalExpenses(@RequestBody AdditionalExpensesRequest additionalExpensesRequest) {
        additionalExpensesService.addAdditionalExpenses(additionalExpensesRequest);
    }

    /**
     * Update additional expenses for a reservation
     * */
    @PutMapping
    public void updateAdditionalExpenses(@RequestBody AdditionalExpensesRequest additionalExpensesRequest) {
        additionalExpensesService.updateAdditionalExpenses(additionalExpensesRequest);
    }
}
