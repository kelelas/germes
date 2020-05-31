package com.kelelas.germes.repository;

import com.kelelas.germes.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM germes.cart WHERE (order_id=:orderId and users_id=:userId)", nativeQuery = true)
    void deleteDishFromUserCart(Long userId, Long orderId);

//    @Query(value = "select d.id, d.name_ukr, d.name_eng , d.image, d.price from dishes as d  " +
//            "  where d.id in (select dishes_id from cart where users_id = :userId);", nativeQuery = true)
//    List<Dish> getCart(Long userId);
    List<Cart> findAllByUserId( Long userId);

//    @Query(value = "select d.id, d.name_ukr as d_name, d.image, d.price from dishes as d " +
//            "  where d.id in (select dishes_id from cart where users_id = :userId);", nativeQuery = true)
//    List<DishDTO> getUkrCart(Integer userId);
//
//    @Query(value = "select d.id, d.name_eng as d_name, d.image, d.price from dishes as d " +
//            "  where d.id in (select dishes_id from cart where users_id = :userId);", nativeQuery = true)
//    List<DishDTO> getEngCart(Integer userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM germes.cart WHERE  (users_id= :userId)", nativeQuery = true)
    void deleteFullUserCart(Long userId);
}
