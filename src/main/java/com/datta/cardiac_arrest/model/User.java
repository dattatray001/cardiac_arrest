package com.datta.cardiac_arrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	private String id;  // ObjectId represented as a String
	private String name;
	@JsonProperty("email")
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private List<String> roles;


}
