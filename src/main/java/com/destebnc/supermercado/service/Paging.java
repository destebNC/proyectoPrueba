package com.destebnc.supermercado.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

final class Paging {

    static final int MAX_PAGE_SIZE = 100;

    private Paging() {
    }

    /** Pagina ordenada por id. Lanza IllegalArgumentException (400) si page < 0 o size < 1. */
    static Pageable of(int page, int size) {
        return PageRequest.of(page, Math.min(size, MAX_PAGE_SIZE), Sort.by("id"));
    }
}
