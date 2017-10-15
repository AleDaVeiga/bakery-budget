package com.nexxera.bakerybudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexxera.bakerybudget.model.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}