package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.CategoryService;
import com.codegym.service.ProducerService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("cart")
@RequestMapping("/products")
public class ProductController {



    @Autowired
    private ProductService productService;

    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProducerService producerService;



    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("producers")
    public Iterable<Producer> producers() {
        return producerService.findAll();
    }

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("")
    public ModelAndView findAll(@RequestParam(required = false) String name) {
    if(name != null) {
            Optional<List<Product>> products = productService.findProductByName(name);
            ModelAndView modelAndView = new ModelAndView("/products/list");
            modelAndView.addObject("products", products.get());
            return modelAndView;
        }
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("products/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

//    @GetMapping("/{id}")
//    public ModelAndView findById(@PathVariable("id") Long id) {
//        Product category = productService.getById(id);
//        ModelAndView modelAndView = new ModelAndView("/category/view");
//        modelAndView.addObject("category", category);
//        return modelAndView;
//    }

    @GetMapping("/create")
    public ModelAndView showCreationForm() {
        ModelAndView modelAndView = new ModelAndView("/products/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(),
                productForm.getDescription(), productForm.getMaterial(), productForm.getWeight(),
                productForm.getProducer()
                 , productForm.getCategories() ,fileName);

        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/products/create");
        modelAndView.addObject("productForm", productForm);
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditionForm(@PathVariable("id") Long id){
        Iterable<Category> categories = categoryService.findAll();
        ProductForm productForm = productService.getById(id);
        ModelAndView modelAndView = new ModelAndView("products/edit");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("productForm", productForm);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateProduct(@ModelAttribute ProductForm productForm ){
        MultipartFile productFormImage = productForm.getImage();
        String fileName = productFormImage.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(),
                productForm.getDescription(), productForm.getMaterial(), productForm.getWeight(), productForm.getProducer()
                , productForm.getCategories(),fileName );

        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/products/edit");

        modelAndView.addObject("productForm", productForm);
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeletionForm(@PathVariable("id") Long id) {
        ProductForm productForm = productService.getById(id);
        ModelAndView modelAndView = new ModelAndView("/products/delete");
        modelAndView.addObject("product", productForm);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("product") Product product) {
        productService.deleteById(product.getId());
        return "redirect:/products";
    }

    @PostMapping  ("")
    private ModelAndView getProductsWithSort(@RequestParam("field") String field ) {
        List<Product> products =  productService.findProductsWithSorting(field);
        ModelAndView modelAndView = new ModelAndView("products/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        ProductForm productForm= (productService.getById(id));

        if (action.equals("show")) {
            cart.addProduct(productForm);
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productForm);
        return "redirect:/products";
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private ModelAndView getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> products = productService.findProductsWithPagination(offset, pageSize);
        ModelAndView modelAndView = new ModelAndView("products/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
