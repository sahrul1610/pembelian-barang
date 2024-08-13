package com.example.pembelian_barang.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.pembelian_barang.model.Item;

@Service
public class PurchaseService {

    public List<Item> findBestCombination(int budget) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Keyboard Logitech", "keyboard", 40000));
        items.add(new Item("Keyboard Razer", "keyboard", 50000));
        items.add(new Item("Mouse Fantech", "mouse", 12000));
        items.add(new Item("Mouse Logitech", "mouse", 20000));
        items.add(new Item("Mouse Razer", "mouse", 35000));

        List<Item> bestCombination = new ArrayList<>();
        int maxSpent = 0;
        List<List<Item>> allCombinations = generateCombinations(items);
        for (List<Item> combination : allCombinations) {
            int totalCost = combination.stream().mapToInt(Item::getPrice).sum();
            if (totalCost <= budget && totalCost > maxSpent) {
                maxSpent = totalCost;
                bestCombination = new ArrayList<>(combination);
            }
        }
        return bestCombination;
    }

    private List<List<Item>> generateCombinations(List<Item> items) {
        List<List<Item>> combinations = new ArrayList<>();
        int n = items.size();
 
        for (int i = 0; i < (1 << n); i++) {
            List<Item> combination = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    combination.add(items.get(j));
                }
            }
            combinations.add(combination);
        }
        return combinations;
    }
}