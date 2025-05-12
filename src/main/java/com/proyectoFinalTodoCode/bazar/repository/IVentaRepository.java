package com.proyectoFinalTodoCode.bazar.repository;

import com.proyectoFinalTodoCode.bazar.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
}
