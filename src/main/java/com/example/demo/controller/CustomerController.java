package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CustomerController {
    private String apiGoBack="";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private InvoiceService invoiceService;

    public Double totalPrice(List<CartItem> list) {
        double total = 0;
        for(CartItem item : list) {
            total += item.getPrice();
        }
        return total;
    }

    @GetMapping("/search")
    public String search(Model model, @Param("keyWord") String keyWord) {
        List<Product> list = productService.getAll();
        if(keyWord != null){
            list = productService.searchProductByName(keyWord);
            model.addAttribute("keyWord", keyWord);
        }
        model.addAttribute("list", list);
        model.addAttribute("apiGoBack", apiGoBack);
        return "search";
    }

    @GetMapping()
    public String index(Model model) {
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

        apiGoBack = "/";

        return "Home";
    }

    // ========= Clothes ========= //
    // ===== Women ===== //
    @GetMapping("/women/ready-to-wear")
    public String GetWomenAll(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 1);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryParentAndGender("Clothes", false);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/women/ready-to-wear");

        // ========= List cart item ========== //
        List<CartItem> listCartItem = cartService.getAllCartItems();
        model.addAttribute("listCartItem", listCartItem);
        model.addAttribute("total", totalPrice(listCartItem));

        apiGoBack = "/women/ready-to-wear";
        return "item";
    }

    @GetMapping("/women/ready-to-wear/dresses")
    public String GetWomenDresses(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 1);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Dresses", false);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/women/ready-to-wear");

        // ========= List cart item ========== //
        List<CartItem> listCartItem = cartService.getAllCartItems();
        model.addAttribute("listCartItem", listCartItem);
        model.addAttribute("total", totalPrice(listCartItem));

        apiGoBack = "/women/ready-to-wear/dresses";
        return "item";
    }

    // ===== Men ===== //
    @GetMapping("/men/ready-to-wear")
    public String GetMenAll(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryParentAndGender("Clothes", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/ready-to-wear");
        return "item";
    }

    @GetMapping("/men/ready-to-wear/jackets-and-coats")
    public String GetMenJacketAndCoat(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Jackets and coats", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/ready-to-wear");
        return "item";
    }

    @GetMapping("/men/ready-to-wear/skirts")
    public String GetMenSkirt(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("skirts", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/ready-to-wear");
        return "item";
    }

    @GetMapping("/men/ready-to-wear/suits")
    public String GetMenSuit(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Clothes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("suits", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/ready-to-wear");
        return "item";
    }

    // ========== Bags ========== //

    @GetMapping("/men/bags")
    public String GetMenBagAll(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryParentAndGender("Bags", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/bags");
        return "item";
    }

    @GetMapping("/men/bags/messenger-bags")
    public String GetMenMessengerBag(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Messenger bags", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/bags");
        return "item";
    }

    @GetMapping("/men/bags/backpacks-and-belt-bags")
    public String GetMenBackpacksAndBeltBag(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Backpacks and Belt Bags", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/bags");
        return "item";
    }

    @GetMapping("/men/bags/totes")
    public String GetMenTotes(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Totes", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/bags");
        return "item";
    }

    @GetMapping("/men/bags/briefcases")
    public String GetMenBriefcases(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Bags", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Briefcases", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/bags");
        return "item";
    }

    // ========== Shoes ========== //
    @GetMapping("/men/shoes")
    public String GetMenShoesAll(Model model){
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

        // ========================== //
        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Shoes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryParentAndGender("Shoes", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/shoes");
        return "item";
    }

    @GetMapping("/men/shoes/sneakers")
    public String GetMenSneakers(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Shoes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Sneakers", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/shoes");
        return "item";
    }

    @GetMapping("/men/shoes/boots")
    public String GetMenBoots(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Shoes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Boots", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/shoes");
        return "item";
    }

    @GetMapping("/men/shoes/driving-shoes")
    public String GetMenDrivingShoes(Model model){
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

        List<Category> listCategory = categoryService.searchCategoryByParentNameAndGenderNot("Shoes", 2);
        model.addAttribute("listCategory", listCategory);
        List<Product> list = productService.getProductByCategoryAndGender("Driving shoes", true);
        model.addAttribute("list", list);
        model.addAttribute("number", list.size());
        model.addAttribute("api", "/men/shoes");
        return "item";
    }

    @GetMapping("/item-detail/{id}")
    public String getItemDetail(Model model, @PathVariable int id){
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

        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "item-detail";
    }

    @GetMapping("/shopping-bag")
    public String getShoppingBag(Model model){
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

        List<Product> list = productService.getProductByCategoryAndGender("Jackets and coats", true);
        model.addAttribute("list", list);
        return "shopping-bag";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
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

        List<Product> list = productService.getProductByCategoryAndGender("Jackets and coats", true);
        model.addAttribute("list", list);

        // ========= Profile ========= //
        User user = SecurityUtils.getPrincipal();
        model.addAttribute("user", user);
        List<Invoice> invoice = invoiceService.findInvoiceByUser(user);
        model.addAttribute("listInvoice", invoice);

        return "profile";
    }
}
