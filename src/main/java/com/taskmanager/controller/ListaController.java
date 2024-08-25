package com.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.model.Item;
import com.taskmanager.model.Lista;
import com.taskmanager.service.ListaService;

@RestController
@RequestMapping("/api/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping
    public List<Lista> getAllListas() {
        return listaService.getAllListas();
    }

    @PostMapping
    public Lista createLista(@RequestBody String nome) {
        return listaService.createLista(nome);
    }

    @PostMapping("/{listaId}/itens")
    public Item addItem(@PathVariable Long listaId, @RequestBody Item item) {
        return listaService.addItem(listaId, item.getTitulo(), item.getDescricao());
    }

    @DeleteMapping("/{id}")
    public void deleteLista(@PathVariable Long id) {
        listaService.deleteLista(id);
    }

    @DeleteMapping("/{listaId}/itens/{itemId}")
    public void deleteItem(@PathVariable Long listaId, @PathVariable Long itemId) {
        listaService.deleteItem(listaId, itemId);
    }
}
