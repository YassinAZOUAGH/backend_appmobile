package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.repository.SubFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubFoodService {

    private final SubFoodRepository subFoodRepository;

    @Autowired
    public SubFoodService(SubFoodRepository subFoodRepository) {
        this.subFoodRepository = subFoodRepository;
    }

    public void deleteDuplicatesByFoodValue(long foodValue) {
        subFoodRepository.deleteDuplicatesByFood(foodValue);
    }

    public Double getMaxPrice(long foodValue) {
        return subFoodRepository.findMaxPrice(foodValue);
    }
    public Double getMinPrice(long foodValue) {
        return subFoodRepository.findMinPrice(foodValue);
    }
}
