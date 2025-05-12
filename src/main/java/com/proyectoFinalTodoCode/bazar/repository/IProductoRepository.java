package com.proyectoFinalTodoCode.bazar.repository;

import com.proyectoFinalTodoCode.bazar.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
