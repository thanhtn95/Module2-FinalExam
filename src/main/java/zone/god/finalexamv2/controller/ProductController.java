package zone.god.finalexamv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zone.god.finalexamv2.model.Category;
import zone.god.finalexamv2.model.Product;
import zone.god.finalexamv2.repository.CategoryRepository;
import zone.god.finalexamv2.service.CategoryService;
import zone.god.finalexamv2.service.ProductService;

import javax.validation.Valid;

@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @ModelAttribute("categories")
    private Iterable<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("")
    public ModelAndView getProductList(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> products = productService.findAll(pageable);
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }

    @GetMapping("/createProduct")
    public ModelAndView getCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }

    @PostMapping("/createProduct")
    public ModelAndView doCreateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("product", product);
            modelAndView.addObject("categories", getCategories());
            modelAndView.addObject("msg", "Invalid Entry!!!");
            return modelAndView;
        }
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        product.setCreateDate(date);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("{id}/viewProduct")
    public ModelAndView getSelectedProductView(@PathVariable int id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("{id}/editProduct")
    public ModelAndView getSelectedProductEditView(@PathVariable int id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", getCategories());
        return modelAndView;
    }

    @PostMapping("/editProduct")
    public ModelAndView doEditProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("product", product);
            modelAndView.addObject("categories", getCategories());
            modelAndView.addObject("msg", "Invalid Entry!!!");
            return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("{id}/deleteProduct")
    public ModelAndView getDeleteProductConfirmPage(@PathVariable int id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("deleteConfirm");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("{id}/doDeleteProduct")
    public ModelAndView doDeleteProduct(@PathVariable int id) {
        productService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("/searchName")
    public ModelAndView getProductsByName(@RequestParam("searchName") String searchName, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> products = productService.findAllByNameContaining(searchName, pageable);
        modelAndView.addObject("products", products);
        modelAndView.addObject("searchName", searchName);
        return modelAndView;
    }
}
