package com.example.taxcal;

import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/income-tax")
public class IncomeTaxController {
    @RequestMapping(value = "/calculate", method = {RequestMethod.GET, RequestMethod.POST})
    public double calculateIncomeTax(@RequestParam double income) {
        // 在这里编写个人所得税计算逻辑
        double tax = 0;
        if (income <= 5000) {
            tax = 0;
        } else if (income <= 41000) {
            tax = (income-5000) * 0.03;
        } else if (income <= 149000) {
            tax = (income-5000) * 0.1 - 2520;
        } else if (income <= 305000) {
            tax = (income-5000) * 0.2 - 16920;
        } else if (income <= 425000) {
            tax = (income-5000) * 0.25 - 31920;
        } else if (income <= 665000) {
            tax = (income-5000) * 0.3 - 52920;
        } else if ( income <= 965000){
            tax = (income-5000) * 0.35 -85920;
        } else {
            tax = (income-5000) * 0.45 - 181920;
        }
        return tax;
    }
}