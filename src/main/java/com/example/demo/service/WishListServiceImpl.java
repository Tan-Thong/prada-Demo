package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.WishList;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WishListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    public WishList getWishList() {
        WishList wishList = wishListRepository.findSingleWishList();
        if (wishList == null) {
            wishList = new WishList();
            wishListRepository.save(wishList);
        }
        return wishList;
    }

    public Optional<WishList> getWishListById(int id) {
        return wishListRepository.findById(id);
    }

    @Override
    public boolean update(WishList wishList) {
        try {
            wishListRepository.save(wishList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> getAllItems() {
        return wishListRepository.findSingleWishList().getItems();
    }

    @Override
    public boolean addItem(Product item) {
        WishList wishList = wishListRepository.findSingleWishList();
        if(wishList.getItems().add(item)) {
            wishListRepository.save(wishList);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItem(Product item) {
        WishList wishList = wishListRepository.findSingleWishList();
        if(wishList.getItems().remove(item)) {
            wishListRepository.save(wishList);
            return true;
        }
        return false;
    }
}
