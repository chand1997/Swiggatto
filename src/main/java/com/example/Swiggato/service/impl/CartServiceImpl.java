package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.FoodRequest;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.MenuItemNotFoundException;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.model.FoodItem;
import com.example.Swiggato.model.MenuItem;
import com.example.Swiggato.repository.CartRepository;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.repository.FoodItemRepository;
import com.example.Swiggato.repository.MenuItemRepository;
import com.example.Swiggato.service.CartService;
import com.example.Swiggato.transformer.FoodItemTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    final CustomerRepository customerRepository;

    final MenuItemRepository menuItemRepository;

    final FoodItemRepository foodItemRepository;

    final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CustomerRepository customerRepository, MenuItemRepository menuItemRepository, FoodItemRepository foodItemRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.foodItemRepository = foodItemRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    @Override
    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest){
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("invalid customer");

        Cart cart=optionalCustomer.get().getCart();

        Optional<MenuItem> optionalMenuItem=menuItemRepository.findById(foodRequest.getMenuItemId());
        if(optionalMenuItem.isEmpty()) throw new MenuItemNotFoundException("invalid menu item id!!");


        MenuItem menuItem=optionalMenuItem.get();

        if(!menuItem.getRestaurant().isOpened()) throw new MenuItemNotFoundException("Restaurant is closed now!!");

        if(!menuItem.isAvailable()) throw new MenuItemNotFoundException("MenuItem not available right now");

        FoodItem foodItem= FoodItemTransformer.FoodRequestToFoodItem(foodRequest,cart,menuItem);

        boolean dishAlreadyPresent=false;

       if(!cart.getFoodItems().isEmpty()){
           if(cart.getFoodItems().get(0).getMenuItem().getRestaurant()
                   .getId()!=foodItem.getMenuItem().getRestaurant().getId()){

               menuItem.setFoodItems(new ArrayList<>());
               cart.setFoodItems(new ArrayList<>());
               foodItemRepository.deleteFoodItemsOfPreviousRestaurant(cart.getId());


           }else{

               for(FoodItem fi:cart.getFoodItems()){
                   if(fi.getMenuItem().getId()==foodItem.getMenuItem().getId()){
                       fi.setRequiredQuantity(fi.getRequiredQuantity()+foodItem.getRequiredQuantity());
                       fi.setTotalCost(fi.getMenuItem().getPrice()*fi.getRequiredQuantity());
                       dishAlreadyPresent=true;
                   }
                   if(dishAlreadyPresent) break;
               }

           }

       }

       if(!dishAlreadyPresent){
           FoodItem savedFoodItem=foodItemRepository.save(foodItem);
           menuItem.getFoodItems().add(savedFoodItem);
           cart.getFoodItems().add(savedFoodItem);
       }

       CartStatusResponse cartStatusResponse=
               FoodItemTransformer.CartToCartStatusResponse(cart,menuItem.getRestaurant());

       cart.setCartTotal(cartStatusResponse.getCartTotal());

        menuItemRepository.save(menuItem);
        cartRepository.save(cart);


      return cartStatusResponse;


    }
}
