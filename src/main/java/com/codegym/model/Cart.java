package com.codegym.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<ProductForm,Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<ProductForm,Integer> products) {
        this.products = products;
    }

    public Map<ProductForm,Integer> getProducts() {
        return products;
    }

    private boolean checkItemInCart(ProductForm productForm){
        for (Map.Entry<ProductForm, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(productForm.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<ProductForm, Integer> selectItemInCart(ProductForm productForm){
        for (Map.Entry<ProductForm, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(productForm.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addProduct(ProductForm productForm){
        if (!checkItemInCart(productForm)){
            products.put(productForm,1);
        } else {
            Map.Entry<ProductForm, Integer> itemEntry = selectItemInCart(productForm);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(),newQuantity);
        }
    }

    public Integer countProductQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<ProductForm, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity(){
        return products.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<ProductForm, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }
}