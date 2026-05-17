package com.salesianostriana.FoodDelivery_Manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.FoodDelivery_Manager.model.Repartidor;
@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor,Long>{

}
