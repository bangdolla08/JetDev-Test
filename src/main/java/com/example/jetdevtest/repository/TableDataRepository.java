package com.example.jetdevtest.repository;

import com.example.jetdevtest.repository.entity.TableData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableDataRepository extends JpaRepository<TableData,String> {
}
