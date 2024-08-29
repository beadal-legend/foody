package com.sparta.baedallegend.menu.repo;

import com.sparta.baedallegend.menu.domain.Menu;
import com.sparta.baedallegend.shop.domain.Shop;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu, UUID> {

	List<Menu> findByShopAndIsPublicTrue(Shop shop);


}
