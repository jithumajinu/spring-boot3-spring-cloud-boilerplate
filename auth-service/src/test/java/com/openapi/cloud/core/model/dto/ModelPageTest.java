package com.openapi.cloud.core.model.dto;

import com.example.acid.web.model.ModelPage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelPageTest {

    @Test
    void testIsHasData() {
        // With data
        ModelPage<String> pageWithData = ModelPage.<String>builder()
                .totalCount(5L)
                .build();
        assertTrue(pageWithData.isHasData());

        // Without data
        ModelPage<String> pageWithoutData = ModelPage.<String>builder()
                .totalCount(0L)
                .build();
        assertFalse(pageWithoutData.isHasData());
    }

    @Test
    void testIsPagingArea() {
        // Multiple pages
        ModelPage<String> multiPageModel = ModelPage.<String>builder()
                .totalPages(3)
                .build();
        assertTrue(multiPageModel.isPagingArea());

        // Single page
        ModelPage<String> singlePageModel = ModelPage.<String>builder()
                .totalPages(1)
                .build();
        assertFalse(singlePageModel.isPagingArea());

        // Zero pages
        ModelPage<String> zeroPageModel = ModelPage.<String>builder()
                .totalPages(0)
                .build();
        assertFalse(zeroPageModel.isPagingArea());
    }

    @Test
    void testGetFromCount() {
        // Normal case
        ModelPage<String> normalPage = ModelPage.<String>builder()
                .pageNumber(3)
                .pageSize(10)
                .totalCount(50L)
                .build();
        assertEquals(21L, normalPage.getFromCount());

        // First page
        ModelPage<String> firstPage = ModelPage.<String>builder()
                .pageNumber(1)
                .pageSize(10)
                .totalCount(50L)
                .build();
        assertEquals(1L, firstPage.getFromCount());

        // Zero total count
        ModelPage<String> emptyPage = ModelPage.<String>builder()
                .pageNumber(3)
                .pageSize(10)
                .totalCount(0L)
                .build();
        assertEquals(0L, emptyPage.getFromCount());

        // Invalid page number
        ModelPage<String> invalidPage = ModelPage.<String>builder()
                .pageNumber(0)
                .pageSize(10)
                .totalCount(50L)
                .build();
        assertEquals(0L, invalidPage.getFromCount());
    }

    @Test
    void testGetToCount() {
        // Normal case with content
        List<String> items = Arrays.asList("item1", "item2", "item3");
        ModelPage<String> normalPage = ModelPage.<String>builder()
                .pageNumber(3)
                .pageSize(10)
                .totalCount(50L)
                .content(items)
                .build();
        assertEquals(23L, normalPage.getToCount());

        // Empty content
        ModelPage<String> emptyContentPage = ModelPage.<String>builder()
                .pageNumber(3)
                .pageSize(10)
                .totalCount(50L)
                .content(Collections.emptyList())
                .build();
        assertEquals(20L, emptyContentPage.getToCount());

        // Zero total count
        ModelPage<String> emptyPage = ModelPage.<String>builder()
                .pageNumber(3)
                .pageSize(10)
                .totalCount(0L)
                .content(items)
                .build();
        assertEquals(0L, emptyPage.getToCount());
    }

    @Test
    void testStaticFactoryMethod() {
        List<String> items = Arrays.asList("item1", "item2");
        ModelPage<String> page = ModelPage.of(10, 2, 5, true, false, 50L, items, null);
        
        assertEquals(10, page.getPageSize());
        assertEquals(2, page.getPageNumber());
        assertEquals(5, page.getTotalPages());
        assertTrue(page.isPrevious());
        assertFalse(page.isNext());
        assertEquals(50L, page.getTotalCount());
        assertEquals(items, page.getContent());
        assertNull(page.getPaginationContent());
    }
}