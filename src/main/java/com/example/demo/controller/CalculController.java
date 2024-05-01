package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/calcul")
public class CalculController {

	@GetMapping("/{op:plus|moins|fois|div}")
	public void calcul(
			@PathVariable(required = false)String op, 
			@RequestParam(value = "valueOne", required = false) String valueOne,
			@RequestParam(value = "valueTwo", required = false) String valueTwo) {
		
		if(!(valueOne.isEmpty() && valueTwo.isEmpty())) {
			int result = 0;
			if(op.equals("plus")) {
				result = Integer.parseInt(valueOne) + Integer.parseInt(valueTwo);
				System.out.println(result);
			}
			
			else if(op.equals("moins")) {
				result = Integer.parseInt(valueOne) - Integer.parseInt(valueTwo);
				System.out.println(result);
			}
			
			else if(op.equals("fois")) {
				result = Integer.parseInt(valueOne) * Integer.parseInt(valueTwo);
				System.out.println(result);
			}
			
			else if(op.equals("div")) {
				try {
					result = Integer.parseInt(valueOne) / Integer.parseInt(valueTwo);
					System.out.println(result);
				}
				catch(Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
		else {
			if(!op.isEmpty()) {
				System.out.println(op);
			}
		}
	}
}