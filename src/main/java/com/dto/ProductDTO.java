package com.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

	private String name;

	private Integer price;

	private String description;

	private String userId;

	private List<String> categoryId;

	private List<byte[]> images;

}
