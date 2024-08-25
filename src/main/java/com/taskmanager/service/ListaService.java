package com.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanager.model.Item;
import com.taskmanager.model.Lista;

@Service
public class ListaService {

    private List<Lista> listas = new ArrayList<>();
    private Long currentListaId = 1L;
    private Long currentItemId = 1L;

    public List<Lista> getAllListas() {
        return listas;
    }

    public Lista createLista(String nome) {
        Lista lista = new Lista(currentListaId++, nome);
        listas.add(lista);
        return lista;
    }

    public Item addItem(Long listaId, String titulo, String descricao) {
        Lista lista = getListaById(listaId);
        if (lista != null) {
            Item item = new Item(currentItemId++, titulo, descricao);
            lista.addItem(item);
            return item;
        }
        return null;
    }

    public Lista getListaById(Long id) {
        return listas.stream().filter(lista -> lista.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteLista(Long id) {
        listas.removeIf(lista -> lista.getId().equals(id));
    }

    public void deleteItem(Long listaId, Long itemId) {
        Lista lista = getListaById(listaId);
        if (lista != null) {
            lista.getItens().removeIf(item -> item.getId().equals(itemId));
        }
    }
}