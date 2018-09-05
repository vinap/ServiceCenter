package com.vinayak.autoServiceCenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinayak.autoServiceCenter.entities.ServiceCenter;

public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Long>{

}
