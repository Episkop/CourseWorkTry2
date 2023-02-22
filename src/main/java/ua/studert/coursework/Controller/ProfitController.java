package ua.studert.coursework.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Service.ProfitService;

@RestController
@RequestMapping("/profit")
public class ProfitController {

    private ProfitService profitService;

    public ProfitController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(profitService.getAllProfit());
        } catch (DBIsEmptyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Data Base is empty!");
        }
    }

    @GetMapping("/one")
    public ResponseEntity getOne(@RequestParam String article) {
        try {
            return ResponseEntity.ok(profitService.findByArticle(article));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not found!");
        }
    }



    @PostMapping("/save")
    public ResponseEntity saveProfit(@RequestBody ProfitModel profit,String email) {
        try {
            return ResponseEntity.ok(profitService.addProfit(profit,email));
        } catch (AlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Line didn`t save");
        }

    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                 @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                 @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                 @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                 @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                 @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                 @RequestParam(required = false) Double december,@RequestParam(required = false) Double sum) {
        try {
            profitService.updateProfit(article, january, february, march, april, may, june, july, august,
                    september, october, november, december,sum);
            return ResponseEntity.ok("Success!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            profitService.deleteProfit(id);
            return ResponseEntity.ok("Successes! The line was deleted! " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Line not found!");
        }
    }
}
