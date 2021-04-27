package fr.eni.contacts.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String text;
	private LocalDate createdAt;
	
	@ManyToOne
	@JsonBackReference
	private Contact contact;

	public Comment(String text) {
		super();
		this.text = text;
		this.createdAt = LocalDate.now();
	}
	
}
