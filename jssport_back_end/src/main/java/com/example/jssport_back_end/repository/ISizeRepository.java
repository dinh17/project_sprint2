package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.model.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeRepository extends JpaRepository<Size,Long> {
}
