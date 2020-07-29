package com.adclear.customervalidator.controller;

import com.adclear.customervalidator.dto.CustomerRequestDto;
import com.adclear.customervalidator.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerSrv;

    @PostMapping("/validate")
    @ApiOperation(value = "To validate incoming requests of customers")
    @ResponseStatus(HttpStatus.OK)
    public void validateCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        customerSrv.checkValidateCustomer(customerRequestDto);
    }

}
