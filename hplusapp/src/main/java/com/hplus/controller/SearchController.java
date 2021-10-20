package com.hplus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.async.DeferredResult;

import com.hplus.model.Login;
import com.hplus.model.Product;
import com.hplus.repository.ProductRepository;

@Controller
public class SearchController {
	@Autowired
	ProductRepository productRepository;
	
	// this helpus in async call that create thread 
	// thred1 by servlet while thread1 is also able to accept new request and handle it.
	// thred2 by spring mvc that perform perform task upload, retrive data and so on.
	@Autowired
	private AsyncTaskExecutor taskExecutor;

	    @GetMapping("/search")
	    public DeferredResult<String> search(@RequestParam("search") String search, Model model, HttpServletRequest request){
	        DeferredResult<String> deferredResult = new DeferredResult<>();
	        System.out.println("in search controller");
	        System.out.println("search criteria: "+search);
	        System.out.println("Async supported in application: "+request.isAsyncSupported());
	        System.out.println("Thread from the servlet container: "+Thread.currentThread().getName());

	       /* return ()->{
	            Thread.sleep(3000);
	            System.out.println("Thread from the spring mvc task executor: "+Thread.currentThread().getName());
	            List<Product> products = new ArrayList<>();
	            products = productRepository.searchByName(search);
	            model.addAttribute("products", products);
	            return "search";
	        };*/

	       taskExecutor.execute(()->{
	           try {
	               Thread.sleep(3000);
	           } catch (InterruptedException e) {
	               e.printStackTrace();
	           }
	           System.out.println("Thread from the spring mvc task executor: "+Thread.currentThread().getName());
	           List<Product> products = new ArrayList<>();
	           products = productRepository.searchByName(search);
	           model.addAttribute("products", products);
//	           if(login.getUsername()==null) {
//	        	   deferredResult.setResult("login");
//	           }
	           deferredResult.setResult("search");
	       });
	        return deferredResult;
	    }
}
