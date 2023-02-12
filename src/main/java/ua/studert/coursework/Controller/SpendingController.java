package ua.studert.coursework.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Service.SpendingService;

@RestController
@RequestMapping("/spending")
public class SpendingController {
    private SpendingService spendingService;

    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }


    @GetMapping
        public ResponseEntity getAll() {
            try {
                return ResponseEntity.ok(spendingService.getAllSpending());
            } catch (DBIsEmptyException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Data Base is empty!");
            }
        }

        @GetMapping("/one")
        public ResponseEntity getOne(@RequestParam String article) {
            try {
                return ResponseEntity.ok(spendingService.findByArticle(article));
            } catch (NotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Not found!");
            }
        }


        @PostMapping("/save")
        public ResponseEntity saveProfit(@RequestBody SpendingEntity spending) {
            try {
                return ResponseEntity.ok(spendingService.addSpending(spending));
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
                                     @RequestParam(required = false) Double december) {
            try {
                spendingService.updateSpending(article, january, february, march, april, may, june, july, august,
                        september, october, november, december);
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
                spendingService.deleteSpending(id);
                return ResponseEntity.ok("Successes! The line was deleted! " + id);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Line not found!");
            }
        }
    }

