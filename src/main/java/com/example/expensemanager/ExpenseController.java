// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.expensemanager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
   origins = {"*"}
)
@RestController
@RequestMapping({"/api/expenses"})
public class ExpenseController {
   @Autowired
   private ExpenseRepository expenseRepository;

   public ExpenseController() {
   }

   @GetMapping
   public List<Expense> getAllExpenses() {
      return this.expenseRepository.findAll();
   }

   @PostMapping
   public Expense createExpense(@RequestBody Expense expense) {
      return (Expense)this.expenseRepository.save(expense);
   }

   @PutMapping({"/{id}"})
   public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
      Expense expense = (Expense)this.expenseRepository.findById(id).orElseThrow(() -> {
         return new RuntimeException("Expense not found with id " + String.valueOf(id));
      });
      expense.setDesc(expenseDetails.getDesc());
      expense.setAmount(expenseDetails.getAmount());
      expense.setDate(expenseDetails.getDate());
      expense.setAmountType(expenseDetails.getAmountType());
      return (Expense)this.expenseRepository.save(expense);
   }

   @DeleteMapping({"/{id}"})
   public void deleteExpense(@PathVariable Long id) {
      this.expenseRepository.deleteById(id);
   }
}
