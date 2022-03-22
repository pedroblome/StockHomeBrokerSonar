package com.pedroblome.stocks.controller;

import java.util.List;
import java.util.Optional;

import com.pedroblome.stocks.controller.dto.StockDto;
import com.pedroblome.stocks.controller.dto.StockRetornodto;
import com.pedroblome.stocks.model.Stock;
import com.pedroblome.stocks.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins= "http://localhost:8081")
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public List<Stock> list() {
        return stockRepository.findAll(Sort.by("id"));
    }

    @GetMapping(value = "/{id}")
    public Optional<Stock> searchStock(@PathVariable Long id) {
        if (stockRepository.existsById(id)) {
            return stockRepository.findById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    ("Id stock is invalid or doenst exists!"));

        }

    }

    // verify the user_order BUY SELL
    @PostMapping(value = "/dto/{id}")
    public boolean dtoStock(@RequestBody StockRetornodto stockRetornodto) {
        Stock stock = stockRepository.getById(stockRetornodto.getId());
        if (stockRepository.findById(stockRetornodto.getId()).isPresent()
                && stockRetornodto.getName().equals(stock.getStockName())
                && stockRetornodto.getSymbol().equals(stock.getStockSymbol())) {
            return true;

        } else {
            return false;

        }

    }

    @PutMapping("/askbid/{id}")
    public ResponseEntity<Stock> updateStock(@RequestBody StockDto stockDto) {
        if (stockRepository.findById(stockDto.getId()).isPresent()) {
            Stock stock = stockRepository.getById(stockDto.getId());
            stock.setAskMax(stockDto.getaskMax());
            stock.setAskMin(stockDto.getaskMin());
            stock.setBidMax(stockDto.getbidMax());
            stock.setBidMin(stockDto.getbidMin());
            stock.setUpdatedOn(stockDto.getupdatedOn());

            Stock stockUpdate = stockRepository.save(stock);

            return new ResponseEntity<>(stockUpdate, HttpStatus.CREATED);

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                ("Id stock is invalid or doenst exists!"));

    }

}