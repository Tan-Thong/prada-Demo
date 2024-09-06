package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @GetMapping("/wish-list")
    public String getAllItems(Model model) {
        // ========= clothes ========= //
        // ==== Men
        List<Category> listCategoryClothesMen = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 2);
        model.addAttribute("listCategoryClothesMen", listCategoryClothesMen);
        model.addAttribute("apiClothesMen", "/men/ready-to-wear");
        // ==== Women
        List<Category> listCategoryClothesWomen = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 1);
        model.addAttribute("listCategoryClothesWomen", listCategoryClothesWomen);
        model.addAttribute("apiClothesWomen", "/women/ready-to-wear");

        // ========= bags ========= //
        // ==== Men
        List<Category> listCategoryBagMen = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 2);
        model.addAttribute("listCategoryBagMen", listCategoryBagMen);
        model.addAttribute("apiBagMen", "/men/bags");
        // ==== Women
        List<Category> listCategoryBagWomen = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 1);
        model.addAttribute("listCategoryBagWomen", listCategoryBagWomen);
        model.addAttribute("apiBagWomen", "/women/bags");

        // ========= shoes ========= //
        // ==== Men
        List<Category> listCategoryShoesMen = categoryService.searchCategoryByParentNameAndGenderNot("Shoes", 2);
        model.addAttribute("listCategoryShoesMen", listCategoryShoesMen);
        model.addAttribute("apiShoesMen", "/men/shoes");
        // ==== Women
        List<Category> listCategoryShoesWomen = categoryService.searchCategoryByParentNameAndGenderNot("Shoes", 1);
        model.addAttribute("listCategoryShoesWomen", listCategoryShoesWomen);
        model.addAttribute("apiShoesWomen", "/women/shoes");

        // ========= List cart item ========== //
        List<CartItem> listCartItem = cartService.getAllCartItems();
        model.addAttribute("listCartItem", listCartItem);
        model.addAttribute("total", totalPrice(listCartItem));

        // ========= Wish list ========== //
        List<Product> listItems = wishListService.getWishList().getItems();
        model.addAttribute("listItems", listItems);
        return "wish-list";
    }

    @PostMapping("/item-detail/insert/{itemId}")
    public ResponseEntity<Void> addItem(@PathVariable("itemId") int itemId) {
        Product item = productService.findById(itemId);
        if (wishListService.getWishList().addItem(item)) {
            item.setLike(true);
            productService.update(item);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/item-detail/delete/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable("itemId") int itemId) {
        Product item = productService.findById(itemId);
        if (wishListService.getWishList().removeItem(item)) {
            item.setLike(false);
            productService.update(item);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/add-to-cart/{id}")
    public String addProductToCart(@PathVariable("id") int productID, @RequestParam("quantity") int quantity, @RequestHeader(value = "Referer", required = false) String referer) {
        CartItem cartItem = cartItemService.getCartItemByProductId(productID);
        Product item = productService.findById(productID);
        if(cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setPrice(cartItem.getPrice() + item.getPrice());
        }
        else {
            cartItem = new CartItem();
            Cart cart = cartService.getCart();
            cartItem.setCart(cart);
            cartItem.setProduct(item);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(item.getPrice());
        }


        if (cartService.addCartItem(cartItem)) {
            return "redirect:" + referer;
        } else {
            return "redirect:/wish-list/e";
        }
    }

    @GetMapping("/remove-to-cart/{id}")
    public String removeProductToCart(@PathVariable("id") int productID, @RequestHeader(value = "Referer", required = false) String referer) {
        CartItem cartItem = cartItemService.getCartItemByProductId(productID);

        if (cartService.removeCartItem(cartItem)) {
            cartItemService.deleteCartItem(cartItem.getId());
            return "redirect:" + referer;
        } else {
            return "redirect:/wish-list/e";
        }
    }

    public Double totalPrice(List<CartItem> list) {
        double total = 0;
        for(CartItem item : list) {
            total += item.getPrice();
        }
        return total;
    }
}
