package com.investment.feign_hystrix.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
	
	private String content;
	
}
