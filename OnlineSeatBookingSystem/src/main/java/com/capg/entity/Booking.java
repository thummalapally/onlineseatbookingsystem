package com.capg.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;


@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	private String seatNumber;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	
	private String bookingAddress;
	
	private int duration;
	
	private String uemail_Id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "u_id", referencedColumnName = "uId")
	private User user;
	
	
	
	public Booking() {
		super();
	}
	
	public Booking(int bookingId, String seatNumber, LocalDate bookingDate, String bookingAddress, int duration,
			String uemail_Id, User user) {
		super();
		this.bookingId = bookingId;
		this.seatNumber = seatNumber;
		this.bookingDate = bookingDate;
		this.bookingAddress = bookingAddress;
		this.duration = duration;
		this.uemail_Id = uemail_Id;
		this.user = user;
	}


	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingAddress() {
		return bookingAddress;
	}

	public void setBookingAddress(String bookingAddress) {
		this.bookingAddress = bookingAddress;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getUemail_Id() {
		return uemail_Id;
	}

	public void setUemail_Id(String uemail_Id) {
		this.uemail_Id = uemail_Id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", seatId=" + seatNumber + ", bookingDate=" + bookingDate
				+ ", bookingAddress=" + bookingAddress + ", duration=" + duration + ", uemail_Id=" + uemail_Id + "]";
	}

}
