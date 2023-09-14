package com.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class OrderDTO {

	private List<String> productIds = new ArrayList<>();

	private String userId;

}
