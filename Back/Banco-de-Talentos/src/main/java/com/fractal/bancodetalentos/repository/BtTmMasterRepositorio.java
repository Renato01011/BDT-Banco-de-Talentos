package com.fractal.bancodetalentos.repository;

import com.fractal.bancodetalentos.model.entity.BtTmMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BtTmMasterRepositorio extends JpaRepository<BtTmMaster, Integer> {

}
