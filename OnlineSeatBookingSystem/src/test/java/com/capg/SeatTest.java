package com.capg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.capg.entity.Seat;
import com.capg.exception.SeatNotFoundException;
import com.capg.repository.SeatRepository;
import com.capg.service.SeatServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SeatTest {

	@Mock
	private SeatRepository seatRepository;

	@InjectMocks
	private SeatServiceImpl seatServiceImpl;

	@Test
	public void testAddSeats() {
		Seat seat = new Seat();

		String result = seatServiceImpl.addSeats(seat);
		assertEquals("Seat added successfully", result);

		verify(seatRepository, times(1)).saveAndFlush(seat);
	}

	@Test
	public void testListAllSeats() throws SeatNotFoundException {
		Seat seat1 = new Seat();

		List<Seat> seats = Arrays.asList(seat1, seat1);

		when(seatRepository.findAll()).thenReturn(seats);

		List<Seat> result = seatServiceImpl.listAllSeats();
		assertEquals(2, result.size());

		verify(seatRepository, times(1)).findAll();
	}
}