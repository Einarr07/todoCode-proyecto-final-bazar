package com.proyectoFinalTodoCode.bazar.repository;

import com.proyectoFinalTodoCode.bazar.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findAllByFechaVenta(LocalDate fecha);
}
