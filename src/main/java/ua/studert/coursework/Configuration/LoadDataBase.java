package ua.studert.coursework.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.studert.coursework.Entity.ProfitEntity;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Service.SpendingService;
import ua.studert.coursework.Service.ProfitService;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner commandLineRunner(final ProfitService profitService,final SpendingService spendingService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                profitService.addProfit(new ProfitEntity("Salary", null, null, null, null, null, null, null, null, null, null, null, null, null));
                profitService.addProfit(new ProfitEntity("Credit", null, null, null, null, null, null, null, null, null, null, null, null, null));
                profitService.addProfit(new ProfitEntity("Deposit interest", null, null, null, null, null, null, null, null, null, null, null, null, null));
                profitService.addProfit(new ProfitEntity("Gifts", null, null, null, null, null, null, null, null, null, null, null, null, null));
                profitService.addProfit(new ProfitEntity("Sell", null, null, null, null, null, null, null, null, null, null, null, null, null));

                spendingService.addSpending(new SpendingEntity("Rental of property", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Communal expenses", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Products", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Alcohol", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Transport", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Car", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Gifts", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Sport", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Heals", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Other", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
                spendingService.addSpending(new SpendingEntity("Credit", null, null, null, null, null, null, null, null, null, null, null, null, null, null));

            }
        };
    }
}

//            private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//            @Bean
//            CommandLineRunner initDatabase(EmployeeRepository repository) {
//
//                return args -> {
//            log.info("Preloading " + repository.save(new ProfitEntity("Salary",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Credit",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Deposit",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Gifts",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//            log.info("Preloading " + repository.save(new ProfitEntity("Sell",null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
//       };

