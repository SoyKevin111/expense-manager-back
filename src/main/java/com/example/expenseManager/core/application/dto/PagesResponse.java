package com.example.expenseManager.core.application.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagesResponse<T> {
   private int pageNumber;
   private int size;
   private long totalElements;
   private int totalPages;
   private List<T> content;
   private boolean last;
}
