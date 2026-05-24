package com.ucsal.estruturaOrganizacional.repository;

import com.ucsal.estruturaOrganizacional.model.CentroDeCusto;
import com.ucsal.estruturaOrganizacional.model.IES;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IESRepository extends JpaRepository<IES, Long> {
}
