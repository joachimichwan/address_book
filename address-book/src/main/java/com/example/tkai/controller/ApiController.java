package com.example.tkai.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.tkai.repository.AddressDb;
import com.example.tkai.rest.BaseResponse;

import com.example.tkai.model.AddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    AddressDb addressDb;

    @GetMapping(value = "/address/getAll")
    public BaseResponse<List<AddressModel>> getAllAddress() {
        List<AddressModel> data = addressDb.findAll();
        BaseResponse<List<AddressModel>> response = new BaseResponse<List<AddressModel>>();
        if (!data.isEmpty()) {
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(data);
        } else {
            response.setStatus(404);
            response.setMessage("not found");
        }
        return response;
    }

    @GetMapping(value = "/address/get/{id}")
    public BaseResponse<AddressModel> getAdressById(@PathVariable(name = "id", required = true) long id) {
        Optional<AddressModel> data = addressDb.findById(id);
        BaseResponse<AddressModel> response = new BaseResponse<>();
        if (data.isPresent()) {
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(data.get());
        } else {
            response.setStatus(404);
            response.setMessage("not found");
        }
        return response;
    }

    @PostMapping(value = "/address/update")
    public BaseResponse<AddressModel> updateAddress(@RequestBody @Valid AddressModel address, BindingResult bindingResult) {
        BaseResponse<AddressModel> response = new BaseResponse<AddressModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        } else {
            Optional<AddressModel> data = addressDb.findById(address.getId());
            if (data.isPresent()) {
                address.setId(data.get().getId());
                address.setName(data.get().getName());
                address.setAddress(data.get().getAddress());
                address.setZipCode(data.get().getZipCode());
                addressDb.save(address);
                response.setStatus(200);
                response.setMessage("success");
                response.setResult(address);
            } else {
                response.setStatus(404);
                response.setMessage("not found");
            }
        }
        return response;
    }

    @PostMapping(value = "/address/create")
    public BaseResponse<AddressModel> addAddress(@RequestBody @Valid AddressModel address, BindingResult bindingResult) {
        BaseResponse<AddressModel> response = new BaseResponse<AddressModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        } else {
            addressDb.save(address);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(address);
        }
        return response;
    }

    @PostMapping(value = "/address/delete/{id}")
    public BaseResponse<AddressModel> deleteAddressById(@PathVariable(name = "id", required = true) long id) {
        Optional<AddressModel> data = addressDb.findById(id);
        BaseResponse<AddressModel> response = new BaseResponse<>();
        if (data.isPresent()) {
            addressDb.delete(data.get());
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(data.get());
        } else {
            response.setStatus(404);
            response.setMessage("not found");
        }
        return response;
    }
}