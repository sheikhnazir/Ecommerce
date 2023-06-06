package com.example.FlipCommerce.service;

import com.example.FlipCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.FlipCommerce.model.Cart;
import com.example.FlipCommerce.model.Customer;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //dto to customer
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        //Each time if new customer is getting registered, cart will be automatically allocated to him
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
        customer.setCart(cart);

        // Saving both customer and cart as they are mapped to each other
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
